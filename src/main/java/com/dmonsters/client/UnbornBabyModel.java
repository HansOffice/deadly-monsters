package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.UnbornBabyEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

/** Modern model-layer port of the original 64x64 Unborn Baby model. */
public final class UnbornBabyModel extends HierarchicalModel<UnbornBabyEntity> {
    private final ModelPart root;
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(DeadlyMonsters.MOD_ID, "unborn_baby"), "main");

    private final ModelPart head;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart leg;

    public UnbornBabyModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.rightArm = root.getChild("right_arm");
        this.leftArm = root.getChild("left_arm");
        this.leg = root.getChild("leg");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition head = root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 11.0F),
                PartPose.offset(0.0F, 12.0F, 0.0F));
        head.addOrReplaceChild("right_eye",
                CubeListBuilder.create().texOffs(39, 17).addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 1.0F),
                PartPose.offset(-3.0F, -4.0F, -5.0F));
        head.addOrReplaceChild("left_eye",
                CubeListBuilder.create().texOffs(39, 12).addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 1.0F),
                PartPose.offset(1.0F, -4.0F, -5.0F));
        head.addOrReplaceChild("mouth",
                CubeListBuilder.create().texOffs(48, 0).addBox(0.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F),
                PartPose.offset(-1.0F, -1.0F, -5.0F));

        root.addOrReplaceChild("body",
                CubeListBuilder.create().texOffs(0, 20).addBox(0.0F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F),
                PartPose.offset(-1.5F, 12.0F, -1.0F));
        root.addOrReplaceChild("body_big",
                CubeListBuilder.create().texOffs(0, 30).addBox(0.0F, 0.0F, 0.0F, 8.0F, 4.0F, 11.0F),
                PartPose.offset(-6.0F, 20.0F, -3.0F));
        root.addOrReplaceChild("body_middle",
                CubeListBuilder.create().texOffs(13, 20).addBox(0.0F, 0.0F, 0.0F, 6.0F, 2.0F, 5.0F),
                PartPose.offset(-4.0F, 18.0F, -2.0F));
        root.addOrReplaceChild("body_left",
                CubeListBuilder.create().texOffs(0, 46).addBox(0.0F, 0.0F, 0.0F, 4.0F, 5.0F, 4.0F),
                PartPose.offset(2.0F, 19.0F, -1.0F));
        root.addOrReplaceChild("right_arm",
                CubeListBuilder.create().texOffs(17, 46).addBox(-2.0F, -9.0F, -1.0F, 2.0F, 9.0F, 2.0F),
                PartPose.offset(-6.0F, 21.0F, 1.533333F));
        root.addOrReplaceChild("left_arm",
                CubeListBuilder.create().texOffs(0, 56).addBox(0.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F),
                PartPose.offset(6.0F, 21.0F, 2.0F));
        root.addOrReplaceChild("leg",
                CubeListBuilder.create().texOffs(26, 46).addBox(-1.0F, -9.0F, 0.0F, 3.0F, 10.0F, 3.0F),
                PartPose.offset(-1.0F, 22.0F, 8.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(UnbornBabyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.leg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.head.yRot = netHeadYaw * (float) (Math.PI / 180.0D);
        this.head.xRot = headPitch * (float) (Math.PI / 180.0D);

        float attack2 = Mth.sin(this.attackTime * (float) Math.PI);
        float attack = Mth.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float) Math.PI);
        this.rightArm.zRot = 0.0F;
        this.leftArm.zRot = 0.0F;
        this.rightArm.yRot = -(0.1F - attack2 * 0.6F);
        this.leftArm.yRot = 0.1F - attack2 * 0.6F;
        float baseArmAngle = -(float) Math.PI / 2.25F;
        this.rightArm.xRot = baseArmAngle + attack2 * 1.2F - attack * 0.4F + 90.0F;
        this.leftArm.xRot = baseArmAngle + attack2 * 1.2F - attack * 0.4F - 80.0F;
        this.rightArm.zRot += Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.leftArm.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.rightArm.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.leftArm.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
    }
}
