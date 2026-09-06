# Deadly Monsters — NeoForge 1.21.1 移植版

这是 Deadly Monsters 从 Minecraft Forge 1.12.2 原生移植到 Minecraft 1.21.1 / NeoForge 的分支

移植尽量保留原版 `dmonsters` 注册 ID、资源、玩法身份和可见行为，同时使用 Minecraft 1.21.1 与 NeoForge 21.1 的原生机制替代旧 Forge API

## 目标环境

- Minecraft **1.21.1**
- NeoForge **21.1.212**
- ModDevGradle **2.0.146**
- Gradle **9.2.1**
- Java **21**
- Mod ID `dmonsters`

## 当前状态

仓库侧 1.21.1 回迁已经完成，并已在 GitHub Actions 使用 JDK 21 执行真实 `gradle build` 验证

构建通过不等于实机验证完成，客户端启动、专用服务器启动、模型观感、自然生成、配置行为和兼容模组联合运行仍按 [`测试清单.md`](测试清单.md) 复验

当前包含

- 12 种原版怪物及实体类型、属性、AI、模型和渲染器
- 12 种原版怪物生成器物品
- 原版方块和特殊行为
- Rebar、四种 Harpoon、Lucky Egg、Dagon 与怪物掉落功能物品
- Lucky Egg 与 Dagon 投射物
- 原版贴图、声音、Logo 与 credits
- 1.21.1 使用的 blockstate、方块模型与 `models/item` 物品模型
- 17 个标准合成配方
- 实体与方块战利品表
- 怪物生命、攻击、速度、自然生成权重和禁用配置
- Mutant Steve、Unborn Baby、Haunted Cow、Topielec 专用配置
- NeoForge biome modifier 自然生成
- Barbed Wire 的 1.21.1 CUTOUT 渲染声明

详细技术决策见 [`移植说明.md`](移植说明.md)

## 配方查看与信息显示

Deadly Monsters 没有自定义配方类型、配方菜单、特殊工作台或 BlockEntity 数据面板

现有 17 个配方全部使用 Minecraft 标准 crafting 体系，因此 JEI 与 REI 不需要 Deadly Monsters 专用桥接层即可读取标准配方

Jade 的基础方块和实体信息同样可以依赖标准注册信息，本分支不添加空壳 provider 或硬依赖

这些结论属于结构兼容判断，联合实机验证状态见 [`兼容性.md`](兼容性.md) 与 [`测试清单.md`](测试清单.md)

## 开发运行

本仓库不包含 Gradle Wrapper

```bash
gradle runClient
gradle runServer
gradle runData
gradle build
```

开发和构建统一使用 JDK 21

GitHub Actions 默认只保留手动构建入口，不在每次推送时自动运行

## 配置

NeoForge 首次运行后会生成 Deadly Monsters 公共配置

`spawnRate` 与 `disabled` 会在生物群系生成列表构建时读取，修改后需要完整重启对应客户端进程或专用服务器进程

其他倍率和行为开关同样按启动时配置状态工作，不提供热重载承诺

## 1.21.1 回迁原则

这是 1.21.1 原生回迁，不是 26.2 API 兼容垫片

- 1.21.1 使用旧实体模型与渲染器签名，不保留 26.2 RenderState 中间层
- 1.21.1 使用 `ResourceLocation`、旧 `MobSpawnType` 与对应实体、方块、物品接口
- Sunlight Drop 与 Haunted Cow 使用 1.21.1 `getDayTime` / `setDayTime` 世界时间接口
- Barbed Wire 在 1.21.1 显式使用 CUTOUT 渲染层，保持原版透明纹理行为
- 1.21.1 继续使用 `assets/dmonsters/models/item`，不保留 1.21.4 才引入的 `assets/<namespace>/items` 物品定义目录
- Topielec 保留深水拖拽的可见行为，同时限制高成本深水搜索刷新频率
- 不重新接入已经过时的 Hostile Worlds Invasions、Mantle、CoroUtil 等旧版硬依赖

## 上游与许可

移植基线与原始资源来自 Deadly Monsters 1.12.2 项目，上游署名继续保留 `bigbang87` 原作者信息

仓库保留原始 MIT License 与版权文本 [`LICENSE`](LICENSE)
