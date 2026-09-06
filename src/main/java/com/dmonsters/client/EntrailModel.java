package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.EntrailEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

/** Modern model-layer port of the original 64x64 Entrail model. */
public final class EntrailModel extends HierarchicalModel<EntrailEntity> {
    private final ModelPart root;
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(DeadlyMonsters.MOD_ID, "entrail"), "main");

    private final ModelPart head;
    private final ModelPart middle;
    private final ModelPart bottom;
    private final ModelPart end;

    public EntrailModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.middle = root.getChild("middle");
        this.bottom = root.getChild("bottom");
        this.end = root.getChild("end");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("middle",
                CubeListBuilder.create().texOffs(19, 0).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 7.0F),
                PartPose.offset(0.0F, 19.0F, -1.0F));
        root.addOrReplaceChild("bottom",
                CubeListBuilder.create().texOffs(46, 0).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 5.0F),
                PartPose.offset(0.0F, 19.0F, 3.0F));
        root.addOrReplaceChild("head",
                CubeListBuilder.create().texOffs(0, 14).addBox(-4.0F, -8.0F, -7.0F, 8.0F, 8.0F, 8.0F),
                PartPose.offset(0.0F, 9.0F, -1.0F));
        root.addOrReplaceChild("end",
                CubeListBuilder.create().texOffs(2, 2).addBox(-1.0F, -1.0F, 4.5F, 2.0F, 2.0F, 4.0F),
                PartPose.offset(0.0F, 19.0F, 5.0F));
        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(EntrailEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.middle.xRot = Mth.cos(ageInTicks * 0.2F + 0.3F * (float) Math.PI) * (float) Math.PI * 0.05F;
        this.middle.y = 5.0F;
        this.bottom.xRot = Mth.cos(ageInTicks * 0.2F + 0.05F * (float) Math.PI) * (float) Math.PI * 0.10F;
        this.bottom.y = Mth.sin(ageInTicks * 0.2F + 0.05F * (float) Math.PI) * (float) Math.PI * 0.2F + 5.0F;
        this.end.xRot = Mth.cos(ageInTicks * 0.2F) * (float) Math.PI * 0.15F;
        this.end.y = Mth.sin(ageInTicks * 0.2F) * (float) Math.PI * 0.4F + 5.0F;
        this.head.yRot = netHeadYaw * (float) (Math.PI / 180.0D);
        this.head.xRot = headPitch * (float) (Math.PI / 180.0D);
    }
}
