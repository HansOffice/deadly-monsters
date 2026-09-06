package com.dmonsters.config;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.HauntedCowEntity;
import com.dmonsters.entity.TopielecEntity;
import com.dmonsters.item.HarpoonItem;
import com.dmonsters.registry.ModEntities;
import com.dmonsters.registry.ModSounds;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.clock.ClockTimeMarkers;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.clock.WorldClocks;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.phys.EntityHitResult;

public final class DeadlyMonstersConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILE_NAME = "dmonsters.json";

    public static final Settings VALUES = new Settings();

    private DeadlyMonstersConfig() {
    }

    public static void load() {
        Path path = FabricLoader.getInstance().getConfigDir().resolve(FILE_NAME);
        try {
            Files.createDirectories(path.getParent());
            if (Files.notExists(path)) {
                writeDefaults(path);
                return;
            }
            try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                JsonElement root = JsonParser.parseReader(reader);
                if (!root.isJsonObject()) {
                    throw new IOException("配置根节点必须是 JSON 对象");
                }
                VALUES.load(root.getAsJsonObject());
            }
        } catch (Exception exception) {
            DeadlyMonsters.LOGGER.error("Failed to load Deadly Monsters Fabric config from {}", path, exception);
        }
    }

    private static void writeDefaults(Path path) throws IOException {
        try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            GSON.toJson(VALUES.toJson(), writer);
        }
    }

    public static boolean naturalSpawnsEnabled(EntityType<?> type) {
        MonsterSettings settings = settingsFor(type);
        return settings == null || !settings.disabled.get();
    }

    public static int spawnRate(EntityType<?> type) {
        MonsterSettings settings = settingsFor(type);
        return settings == null ? 0 : settings.spawnRate.get();
    }

    public static void onEntityJoinLevel(Entity entity, boolean loadedFromDisk) {
        if (!(entity instanceof LivingEntity living)) {
            return;
        }

        MonsterScale scale = scaleFor(living.getType());
        if (scale == null) {
            return;
        }

        float previousHealth = living.getHealth();
        float previousMaxHealth = living.getMaxHealth();
        boolean previouslyFull = Math.abs(previousHealth - previousMaxHealth) < 0.001F;

        double speed = scale.baseSpeed * VALUES.globalSpeedMultiplier.get() * scale.settings.speedMultiplier.get();
        double strength = scale.baseStrength * VALUES.globalStrengthMultiplier.get() * scale.settings.strengthMultiplier.get();
        double health = scale.baseHealth * VALUES.globalHealthMultiplier.get() * scale.settings.healthMultiplier.get();

        setBaseValue(living, Attributes.MOVEMENT_SPEED, speed);
        setBaseValue(living, Attributes.ATTACK_DAMAGE, strength);
        setBaseValue(living, Attributes.MAX_HEALTH, health);

        if (!loadedFromDisk && previouslyFull) {
            living.setHealth(living.getMaxHealth());
        } else {
            living.setHealth(Math.min(previousHealth, living.getMaxHealth()));
        }
    }

    private static void setBaseValue(LivingEntity living, Holder<Attribute> attribute, double value) {
        AttributeInstance instance = living.getAttribute(attribute);
        if (instance != null) {
            instance.setBaseValue(value);
        }
    }

    public static InteractionResult onPlayerAttack(
            Player player,
            Level level,
            InteractionHand hand,
            Entity target,
            EntityHitResult hitResult) {
        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }

        if (target instanceof TopielecEntity
                && !VALUES.topielec.disabled.get()
                && VALUES.topielecHarpoonOnly.get()
                && !(player.getMainHandItem().getItem() instanceof HarpoonItem)) {
            return InteractionResult.FAIL;
        }

        if (!(target instanceof HauntedCowEntity)
                || VALUES.hauntedCow.disabled.get()
                || VALUES.hauntedCowDisableTimeChange.get()) {
            return InteractionResult.PASS;
        }

        ItemStack held = player.getMainHandItem();
        if (held.is(ItemTags.SWORDS) || held.is(Items.BOW) || isConfiguredHauntedCowWeapon(held.getItem())) {
            return InteractionResult.PASS;
        }

        if (!(level instanceof ServerLevel playerLevel)) {
            return InteractionResult.PASS;
        }
        ServerLevel overworld = playerLevel.getServer().overworld();
        long time = overworld.getOverworldClockTime() % 24000L;
        if (time >= 13000L) {
            return InteractionResult.PASS;
        }

        if (overworld.getGameRules().get(GameRules.ADVANCE_TIME)) {
            Registry<WorldClock> clocks = overworld.registryAccess().lookupOrThrow(Registries.WORLD_CLOCK);
            Holder<WorldClock> overworldClock = clocks.getOrThrow(WorldClocks.OVERWORLD);
            overworld.clockManager().moveToTimeMarker(overworldClock, ClockTimeMarkers.NIGHT);
        }
        player.playSound(ModSounds.HAUNTEDCOW_TIMECHANGE.get(), 1.0F, 1.0F);
        player.sendSystemMessage(Component.translatable("msg.dmonsters.haunted_cow").withStyle(ChatFormatting.DARK_RED));
        return InteractionResult.PASS;
    }

    private static boolean isConfiguredHauntedCowWeapon(Item item) {
        String id = BuiltInRegistries.ITEM.getKey(item).toString();
        return VALUES.hauntedCowValidWeapons.get().contains(id);
    }

    private static MonsterSettings settingsFor(EntityType<?> type) {
        MonsterScale scale = scaleFor(type);
        return scale == null ? null : scale.settings;
    }

    private static MonsterScale scaleFor(EntityType<?> type) {
        if (type == ModEntities.MUTANT_STEVE.get()) return new MonsterScale(0.13D, 16.0D, 40.0D, VALUES.mutantSteve);
        if (type == ModEntities.FREEZER.get()) return new MonsterScale(0.13D, 16.0D, 45.0D, VALUES.freezer);
        if (type == ModEntities.CLIMBER.get()) return new MonsterScale(0.10D, 12.0D, 24.0D, VALUES.climber);
        if (type == ModEntities.ENTRAIL.get()) return new MonsterScale(0.30D, 10.0D, 30.0D, VALUES.entrail);
        if (type == ModEntities.UNBORN_BABY.get()) return new MonsterScale(0.25D, 12.0D, 40.0D, VALUES.unbornBaby);
        if (type == ModEntities.FALLEN_LEADER.get()) return new MonsterScale(0.10D, 20.0D, 60.0D, VALUES.fallenLeader);
        if (type == ModEntities.BLOODY_MAIDEN.get()) return new MonsterScale(0.20D, 4.0D, 20.0D, VALUES.bloodyMaiden);
        if (type == ModEntities.ZOMBIE_CHICKEN.get()) return new MonsterScale(0.26D, 8.0D, 16.0D, VALUES.zombieChicken);
        if (type == ModEntities.PRESENT.get()) return new MonsterScale(0.30D, 1.0D, 26.0D, VALUES.present);
        if (type == ModEntities.STRANGER.get()) return new MonsterScale(0.15D, 4.0D, 40.0D, VALUES.stranger);
        if (type == ModEntities.HAUNTED_COW.get()) return new MonsterScale(0.30D, 12.0D, 24.0D, VALUES.hauntedCow);
        if (type == ModEntities.TOPIELEC.get()) return new MonsterScale(0.40D, 1.0D, 20.0D, VALUES.topielec);
        return null;
    }

    private record MonsterScale(double baseSpeed, double baseStrength, double baseHealth, MonsterSettings settings) {
    }

    public static final class Settings {
        public final ConfigValue<Double> globalHealthMultiplier = new ConfigValue<>(1.0D);
        public final ConfigValue<Double> globalStrengthMultiplier = new ConfigValue<>(1.0D);
        public final ConfigValue<Double> globalSpeedMultiplier = new ConfigValue<>(1.0D);

        public final MonsterSettings mutantSteve = new MonsterSettings(8);
        public final MonsterSettings freezer = new MonsterSettings(8);
        public final MonsterSettings climber = new MonsterSettings(8);
        public final MonsterSettings entrail = new MonsterSettings(12);
        public final MonsterSettings unbornBaby = new MonsterSettings(12);
        public final MonsterSettings fallenLeader = new MonsterSettings(12);
        public final MonsterSettings bloodyMaiden = new MonsterSettings(12);
        public final MonsterSettings zombieChicken = new MonsterSettings(12);
        public final MonsterSettings present = new MonsterSettings(12);
        public final MonsterSettings stranger = new MonsterSettings(12);
        public final MonsterSettings hauntedCow = new MonsterSettings(8);
        public final MonsterSettings topielec = new MonsterSettings(8);

        public final ConfigValue<Boolean> mutantSteveBreakBlocks = new ConfigValue<>(true);
        public final ConfigValue<Boolean> babyBlindness = new ConfigValue<>(false);
        public final ConfigValue<Integer> topielecSearchDistance = new ConfigValue<>(16);
        public final ConfigValue<Boolean> topielecHarpoonOnly = new ConfigValue<>(false);
        public final ConfigValue<List<String>> hauntedCowValidWeapons =
                new ConfigValue<>(List.of("thaumicaugmentation:morphic_tool"));
        public final ConfigValue<Boolean> hauntedCowDisableTimeChange = new ConfigValue<>(false);

        private void load(JsonObject root) {
            JsonObject general = section(root, "general");
            globalHealthMultiplier.set(readDouble(general, "globalHealthMultiplier", globalHealthMultiplier.get(), 0.01D, 100.0D));
            globalStrengthMultiplier.set(readDouble(general, "globalStrengthMultiplier", globalStrengthMultiplier.get(), 0.01D, 100.0D));
            globalSpeedMultiplier.set(readDouble(general, "globalSpeedMultiplier", globalSpeedMultiplier.get(), 0.01D, 10.0D));

            mutantSteve.load(section(root, "mutant_steve"));
            freezer.load(section(root, "freezer"));
            climber.load(section(root, "climber"));
            entrail.load(section(root, "entrail"));
            unbornBaby.load(section(root, "unborn_baby"));
            fallenLeader.load(section(root, "fallen_leader"));
            bloodyMaiden.load(section(root, "bloody_maiden"));
            zombieChicken.load(section(root, "zombie_chicken"));
            present.load(section(root, "present"));
            stranger.load(section(root, "stranger"));
            hauntedCow.load(section(root, "haunted_cow"));
            topielec.load(section(root, "topielec"));

            JsonObject mutant = section(root, "mutant_steve");
            mutantSteveBreakBlocks.set(readBoolean(mutant, "breakBlocks", mutantSteveBreakBlocks.get()));

            JsonObject baby = section(root, "unborn_baby");
            babyBlindness.set(readBoolean(baby, "blindness", babyBlindness.get()));

            JsonObject topielecSection = section(root, "topielec");
            topielecSearchDistance.set(readInt(topielecSection, "searchDistance", topielecSearchDistance.get(), 1, 128));
            topielecHarpoonOnly.set(readBoolean(topielecSection, "harpoonOnly", topielecHarpoonOnly.get()));

            JsonObject haunted = section(root, "haunted_cow");
            hauntedCowValidWeapons.set(readStrings(haunted, "validWeapons", hauntedCowValidWeapons.get()));
            hauntedCowDisableTimeChange.set(readBoolean(haunted, "disableTimeChange", hauntedCowDisableTimeChange.get()));
        }

        private JsonObject toJson() {
            JsonObject root = new JsonObject();
            JsonObject general = new JsonObject();
            general.addProperty("globalHealthMultiplier", globalHealthMultiplier.get());
            general.addProperty("globalStrengthMultiplier", globalStrengthMultiplier.get());
            general.addProperty("globalSpeedMultiplier", globalSpeedMultiplier.get());
            root.add("general", general);

            root.add("mutant_steve", mutantSteve.toJson());
            root.add("freezer", freezer.toJson());
            root.add("climber", climber.toJson());
            root.add("entrail", entrail.toJson());
            root.add("unborn_baby", unbornBaby.toJson());
            root.add("fallen_leader", fallenLeader.toJson());
            root.add("bloody_maiden", bloodyMaiden.toJson());
            root.add("zombie_chicken", zombieChicken.toJson());
            root.add("present", present.toJson());
            root.add("stranger", stranger.toJson());
            root.add("haunted_cow", hauntedCow.toJson());
            root.add("topielec", topielec.toJson());

            root.getAsJsonObject("mutant_steve").addProperty("breakBlocks", mutantSteveBreakBlocks.get());
            root.getAsJsonObject("unborn_baby").addProperty("blindness", babyBlindness.get());
            root.getAsJsonObject("topielec").addProperty("searchDistance", topielecSearchDistance.get());
            root.getAsJsonObject("topielec").addProperty("harpoonOnly", topielecHarpoonOnly.get());

            JsonArray weapons = new JsonArray();
            hauntedCowValidWeapons.get().forEach(weapons::add);
            root.getAsJsonObject("haunted_cow").add("validWeapons", weapons);
            root.getAsJsonObject("haunted_cow").addProperty("disableTimeChange", hauntedCowDisableTimeChange.get());
            return root;
        }
    }

    public static final class MonsterSettings {
        public final ConfigValue<Double> healthMultiplier = new ConfigValue<>(1.0D);
        public final ConfigValue<Double> strengthMultiplier = new ConfigValue<>(1.0D);
        public final ConfigValue<Double> speedMultiplier = new ConfigValue<>(1.0D);
        public final ConfigValue<Integer> spawnRate;
        public final ConfigValue<Boolean> disabled = new ConfigValue<>(false);

        private MonsterSettings(int defaultSpawnRate) {
            this.spawnRate = new ConfigValue<>(defaultSpawnRate);
        }

        private void load(JsonObject section) {
            healthMultiplier.set(readDouble(section, "healthMultiplier", healthMultiplier.get(), 0.01D, 100.0D));
            strengthMultiplier.set(readDouble(section, "strengthMultiplier", strengthMultiplier.get(), 0.01D, 100.0D));
            speedMultiplier.set(readDouble(section, "speedMultiplier", speedMultiplier.get(), 0.01D, 10.0D));
            spawnRate.set(readInt(section, "spawnRate", spawnRate.get(), 0, 1000));
            disabled.set(readBoolean(section, "disabled", disabled.get()));
        }

        private JsonObject toJson() {
            JsonObject object = new JsonObject();
            object.addProperty("healthMultiplier", healthMultiplier.get());
            object.addProperty("strengthMultiplier", strengthMultiplier.get());
            object.addProperty("speedMultiplier", speedMultiplier.get());
            object.addProperty("spawnRate", spawnRate.get());
            object.addProperty("disabled", disabled.get());
            return object;
        }
    }

    public static final class ConfigValue<T> {
        private T value;

        private ConfigValue(T value) {
            this.value = value;
        }

        public T get() {
            return this.value;
        }

        private void set(T value) {
            this.value = value;
        }
    }

    private static JsonObject section(JsonObject root, String name) {
        JsonElement element = root.get(name);
        return element != null && element.isJsonObject() ? element.getAsJsonObject() : new JsonObject();
    }

    private static double readDouble(JsonObject object, String key, double fallback, double min, double max) {
        try {
            double value = object.has(key) ? object.get(key).getAsDouble() : fallback;
            return Math.max(min, Math.min(max, value));
        } catch (RuntimeException ignored) {
            return fallback;
        }
    }

    private static int readInt(JsonObject object, String key, int fallback, int min, int max) {
        try {
            int value = object.has(key) ? object.get(key).getAsInt() : fallback;
            return Math.max(min, Math.min(max, value));
        } catch (RuntimeException ignored) {
            return fallback;
        }
    }

    private static boolean readBoolean(JsonObject object, String key, boolean fallback) {
        try {
            return object.has(key) ? object.get(key).getAsBoolean() : fallback;
        } catch (RuntimeException ignored) {
            return fallback;
        }
    }

    private static List<String> readStrings(JsonObject object, String key, List<String> fallback) {
        JsonElement element = object.get(key);
        if (element == null || !element.isJsonArray()) {
            return fallback;
        }
        List<String> values = new ArrayList<>();
        for (JsonElement entry : element.getAsJsonArray()) {
            if (entry.isJsonPrimitive() && entry.getAsJsonPrimitive().isString()) {
                values.add(entry.getAsString());
            }
        }
        return values.isEmpty() ? fallback : List.copyOf(values);
    }
}
