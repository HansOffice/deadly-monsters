package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.FallenLeaderEntity;
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

/** Modern model-layer port of the original 64x64 Fallen Leader model. */
public final class FallenLeaderModel extends HierarchicalModel<FallenLeaderEntity> {
    private final ModelPart root;
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(DeadlyMonsters.MOD_ID, "fallen_leader"), "main");

    private final ModelPart head;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;

    public FallenLeaderModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.leftLeg = root.getChild("left_leg");
        this.rightLeg = root.getChild("right_leg");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("middle_body",
                CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 10.0F, 5.0F, 7.0F),
                PartPose.offset(-5.0F, 2.0F, -3.0F));

        PartDefinition leftLeg = root.addOrReplaceChild("left_leg",
                CubeListBuilder.create().texOffs(35, 0).addBox(0.0F, 0.0F, -3.0F, 4.0F, 10.0F, 5.0F),
                PartPose.offset(5.0F, 4.0F, 1.0F));
        leftLeg.addOrReplaceChild("left_lower_leg",
                CubeListBuilder.create().texOffs(23, 13).addBox(-5.0F, -4.0F, -1.0F, 2.0F, 8.0F, 3.0F),
                PartPose.offset(6.0F, 14.0F, -1.0F));
        leftLeg.addOrReplaceChild("left_foot",
                CubeListBuilder.create().texOffs(39, 32).addBox(-5.0F, -4.0F, -1.0F, 4.0F, 2.0F, 7.0F),
                PartPose.offset(5.0F, 22.0F, -4.0F));

        PartDefinition rightLeg = root.addOrReplaceChild("right_leg",
                CubeListBuilder.create().texOffs(35, 16).addBox(-4.0F, 0.0F, -3.0F, 4.0F, 10.0F, 5.0F),
                PartPose.offset(-5.0F, 4.0F, 1.0F));
        rightLeg.addOrReplaceChild("right_lower_leg",
                CubeListBuilder.create().texOffs(25, 33).addBox(5.0F, -4.0F, -1.0F, 2.0F, 8.0F, 3.0F),
                PartPose.offset(-8.0F, 14.0F, -1.0F));
        rightLeg.addOrReplaceChild("right_foot",
                CubeListBuilder.create().texOffs(39, 42).addBox(5.0F, -4.0F, -1.0F, 4.0F, 2.0F, 7.0F),
                PartPose.offset(-9.0F, 22.0F, -4.0F));

        root.addOrReplaceChild("lower_body",
                CubeListBuilder.create().texOffs(0, 13).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 6.0F, 5.0F),
                PartPose.offsetAndRotation(0.0F, 6.0F, 1.0F, -0.3490659F, 0.0F, 0.0F));
        root.addOrReplaceChild("neck",
                CubeListBuilder.create().texOffs(0, 25).addBox(0.0F, 0.0F, 0.0F, 4.0F, 6.0F, 3.0F),
                PartPose.offsetAndRotation(-2.0F, 10.0F, -3.0F, -0.3490659F, 0.0F, 0.0F));
        root.addOrReplaceChild("head",
                CubeListBuilder.create().texOffs(0, 35).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 6.0F, 6.0F),
                PartPose.offset(0.0F, 15.0F, -4.0F));
        root.addOrReplaceChild("upper_body",
                CubeListBuilder.create().texOffs(0, 48).addBox(0.0F, 0.0F, 0.0F, 6.0F, 3.0F, 5.0F),
                PartPose.offset(-3.0F, -1.0F, -2.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(FallenLeaderEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.head.yRot = netHeadYaw * (float) (Math.PI / 180.0D);
        this.head.xRot = headPitch * (float) (Math.PI / 180.0D);
    }
}
