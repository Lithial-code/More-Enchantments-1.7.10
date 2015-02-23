package com.lithial.me.handlers.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.Vec3;


public class ArrowEntityTracker {
    public EntityArrow arrow = null;
    public Entity target = null;
    public int entityTrackingTicks = 0;
    public double speed;
    private Vec3 targetCoord;
    public boolean unsetTargetCoord = true;
    public Entity originalShootingEntity = null;

    public ArrowEntityTracker(EntityArrow arrow, Vec3 targetCoord, EntityLiving target) {
        this.entityTrackingTicks = 20;
        this.targetCoord = targetCoord;
        this.target = target;
        this.arrow = arrow;
        this.originalShootingEntity = arrow.shootingEntity;
        this.speed = 1.5;
    }

    public void update(boolean homeIn) {
        // System.out.println("Updating");
        if (this.originalShootingEntity != arrow.shootingEntity) {
            return;
        }
        entityTrackingTicks = entityTrackingTicks - 1;
        if (entityTrackingTicks > 0 && target != null && homeIn) {
            if (target.isDead) {
                target = null;
            } else {
                targetCoord.xCoord = target.posX;
                targetCoord.yCoord = target.posY + target.height * 3 / 4;
                targetCoord.zCoord = target.posZ;
            }
        }
        double diffX = targetCoord.xCoord - arrow.posX;
        double diffY = targetCoord.yCoord - arrow.posY;
        double diffZ = targetCoord.zCoord - arrow.posZ;
        Vec3 vecTargetDir = (Vec3.createVectorHelper(diffX, diffY, diffZ)).normalize();
        if (arrow != null) {

            arrow.motionX = vecTargetDir.xCoord * speed;
            arrow.motionY = vecTargetDir.yCoord * speed;
            arrow.motionZ = vecTargetDir.zCoord * speed;
        }
    }

    public void setTargetCoord(double x, double y, double z) {
        unsetTargetCoord = false;
        targetCoord.xCoord = x;
        targetCoord.yCoord = y;
        targetCoord.zCoord = z;
    }

    public void setEntityTracked(Entity newTarget) {
        entityTrackingTicks = 10;

        this.target = newTarget;
    }
}