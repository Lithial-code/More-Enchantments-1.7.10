package com.lithial.me.handlers;

import java.util.Random;

import com.lithial.me.enchantments.EnchantmentCore;
import com.lithial.me.enchantments.Utils;
import com.lithial.me.handlers.utils.ArrowEntityTracker;
import com.lithial.me.handlers.utils.OpenGlHelper;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockGlowstone;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;

import com.lithial.me.enchantments.EffectManager;
import com.lithial.me.enchantments.Enchantments;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import static net.minecraft.util.MathHelper.*;

public class ArrowHandler {
    public static Enchantments enchant;

    @SubscribeEvent
    public void onShoot(ArrowLooseEvent event) {
        if (enchant.allowQuickDraw)
            if (event.entityLiving instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.entityLiving;
                int quickdraw = Utils.getEnchHelp(player, Enchantments.quickdraw.effectId, player.getHeldItem());
                if (quickdraw != 0)
                    event.charge *= 1 + (int) (Math.ceil(quickdraw * 0.5));

            }
    }

    @SubscribeEvent
    public void quickDraw(LivingEvent.LivingUpdateEvent event) {
        if (enchant.allowQuickDraw) {
            if (event.entityLiving instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.entityLiving;
                World world = player.worldObj;
                int quickdraw = Utils.getEnchHelp(player, Enchantments.quickdraw.effectId, player.getHeldItem());
                if (quickdraw != 0) {
                    EffectManager.QuickDraw(player, world);
                }
            }
        }
    }

    public EntityArrow trackingArrow = null;
    public ArrowEntityTracker activeTrackingArrowData;
    public int trackingArrow_maxDist = 160;
   static EnchantmentCore core;
    @SubscribeEvent
    public void onUpdate(LivingEvent.LivingUpdateEvent event) {

        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            if (player == null)
                return;

            final int SharpShooter = Utils.getEnchHelp(player,Enchantments.sharpShooter.effectId, player.getHeldItem());
            final int Homing = Utils.getEnchHelp(player,Enchantments.homing.effectId, player.getHeldItem());

                boolean homeIn = (Homing != 0);


            if (SharpShooter != 0 ){
                manageArrowTracking(homeIn, player);
            }
            if(homeIn && SharpShooter==0)
                player.getHeldItem().addEnchantment(Enchantments.sharpShooter,1);
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean canArrowBeTracked(EntityArrow arrow, EntityPlayer player) {
        if (arrow == null)
            return false;
        try {
            return (player.getDistanceToEntity(arrow) < trackingArrow_maxDist && arrow.shootingEntity == player && (ObfuscationReflectionHelper.getPrivateValue(EntityArrow.class, arrow, 5)).equals(false) && arrow.isDead == false && isArrowCritical(arrow));
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Check if an arrow is critically charged
    public static boolean isArrowCritical(EntityArrow arrow) {
        if (arrow != null) {
            return arrow.getIsCritical();
        }
        return false;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean trySetTrackingArrow(EntityArrow newArrow, EntityPlayer player) {
        if ((canArrowBeTracked(trackingArrow, player) == false || newArrow.ticksExisted < trackingArrow.ticksExisted) && canArrowBeTracked(newArrow, player)) {
            trackingArrow = newArrow;
            activeTrackingArrowData = new ArrowEntityTracker(newArrow, Vec3.createVectorHelper(0, 0, 0), null);
            return true;
        }
        return false;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void manageArrowTracking(boolean homeIn, EntityPlayer player) {
        EntityArrow arrow = null;

        boolean newArrow = false;
        Object[] list = player.worldObj.loadedEntityList.toArray();
        for (Object o : list) {
            if (o instanceof EntityArrow) {
                arrow = (EntityArrow) o;
                newArrow = trySetTrackingArrow(arrow, player);
            }
        }
        if (!canArrowBeTracked(trackingArrow, player) || activeTrackingArrowData == null) {
            return;
        }
        Vec3 lookVector = player.getLook(1.0F).normalize();
        Vec3 start = Vec3.createVectorHelper(player.posX, player.posY + player.getEyeHeight(), player.posZ);
        Vec3 end = Vec3.createVectorHelper(start.xCoord + lookVector.xCoord * trackingArrow_maxDist * 1000, start.yCoord + lookVector.yCoord * trackingArrow_maxDist * 1000, start.zCoord + lookVector.zCoord * trackingArrow_maxDist * 1000);
        Vec3 arrowPos = Vec3.createVectorHelper(trackingArrow.posX, trackingArrow.posY, trackingArrow.posZ);
        double speed = 1.5f;
        ////////////////////
        //Sharpshooter
        /////////////////////
        if(homeIn == false) {
            speed = 4f;
        }
        activeTrackingArrowData.speed = speed;
        double mag = Math.sqrt((end.yCoord - start.yCoord) * (end.yCoord - start.yCoord) + (end.zCoord - start.zCoord) * (end.zCoord - start.zCoord) + (end.xCoord - start.xCoord) * (end.xCoord - start.xCoord));
        double targetX = end.xCoord;
        double targetY = end.yCoord;
        double targetZ = end.zCoord;
        Vec3 target = Vec3.createVectorHelper(targetX, targetY, targetZ);

        MovingObjectPosition m = rayTraceBlocks(start, target, player.worldObj, 0);
        if (m != null) {

            target.xCoord = m.blockX + 0.5;
            target.yCoord = m.blockY + 0.5;
            target.zCoord = m.blockZ + 0.5;
        }
        Vec3 vec = Vec3.createVectorHelper(0, 0, 0);
        start = Vec3.createVectorHelper(player.posX, player.posY + player.getEyeHeight(), player.posZ);
        end = Vec3.createVectorHelper(start.xCoord + lookVector.xCoord * trackingArrow_maxDist, start.yCoord + lookVector.yCoord * trackingArrow_maxDist, start.zCoord + lookVector.zCoord * trackingArrow_maxDist);
        for (Object o : player.worldObj.loadedEntityList) {
            double distFromEntityTracked = trackingArrow_maxDist * trackingArrow_maxDist;
            if (o instanceof Entity && !(o.equals(trackingArrow)) && trackingArrow.shootingEntity != o) {
                Entity le = (Entity) o;
                if (distFromEntityTracked > player.getDistanceToEntity(le)) {

                    Vec3 touchingEntityAt = OpenGlHelper.lineIntersectionEntity(start, target, le);
                    if (touchingEntityAt != null) {
                        distFromEntityTracked = player.getDistanceToEntity(le);
                        target.xCoord = touchingEntityAt.xCoord;
                        target.yCoord = touchingEntityAt.yCoord;
                        target.zCoord = touchingEntityAt.zCoord;
                        if (player.isSneaking()) {
                            activeTrackingArrowData.setEntityTracked(le);
                        }
                    }
                }
            }
        }

        Vec3 solution = OpenGlHelper.closestLinePartToPoint(start, end, target);
        if (solution == null)
            return;



            ////////////////
            //homing
            ////////////////

        else if (homeIn) {

            if ((activeTrackingArrowData.unsetTargetCoord || (!player.isSneaking())) && activeTrackingArrowData.target == null) {
                activeTrackingArrowData.setTargetCoord(solution.xCoord, solution.yCoord, solution.zCoord);
            }
        } else if (activeTrackingArrowData.unsetTargetCoord) {
            activeTrackingArrowData.setTargetCoord(solution.xCoord, solution.yCoord, solution.zCoord);
        }
        activeTrackingArrowData.update(homeIn);
    }
    public static Random rand = new Random();
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static MovingObjectPosition rayTraceBlocks(Vec3 par1Vec3D, Vec3 par2Vec3D, World world, int allowableBlocksType) {
        return rayTraceBlocks_do_do(par1Vec3D, par2Vec3D, false, false, world, allowableBlocksType);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static MovingObjectPosition rayTraceBlocks_do(Vec3 par1Vec3D, Vec3 par2Vec3D, boolean par3, World world, int allowableBlocksType) {
        return rayTraceBlocks_do_do(par1Vec3D, par2Vec3D, par3, false, world, allowableBlocksType);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static MovingObjectPosition rayTraceBlocks_do_do(Vec3 par1Vec3D, Vec3 par2Vec3D, boolean par3, boolean par4, World world, int allowableBlocksType) {
        if (Double.isNaN(par1Vec3D.xCoord) || Double.isNaN(par1Vec3D.yCoord) || Double.isNaN(par1Vec3D.zCoord)) {
            return null;
        }

        if (Double.isNaN(par2Vec3D.xCoord) || Double.isNaN(par2Vec3D.yCoord) || Double.isNaN(par2Vec3D.zCoord)) {
            return null;
        }
        int i = MathHelper.floor_double(par2Vec3D.xCoord);
        int j = MathHelper.floor_double(par2Vec3D.yCoord);
        int k = MathHelper.floor_double(par2Vec3D.zCoord);
        int l = MathHelper.floor_double(par1Vec3D.xCoord);
        int i1 = MathHelper.floor_double(par1Vec3D.yCoord);
        int j1 = MathHelper.floor_double(par1Vec3D.zCoord);
        Block k1 = world.getBlock(l, i1, j1);
        int i2 = world.getBlockMetadata(l, i1, j1);
       // Block block = Block.blocksList[k1];
        if ((!par4 || k1 == null || k1.getCollisionBoundingBoxFromPool(world, l, i1, j1) != null) && k1!= Block.getBlockById(0) && isBlockAllowable(k1, i2, par3, allowableBlocksType)) {
            MovingObjectPosition movingobjectposition = k1.collisionRayTrace(world, l, i1, j1, par1Vec3D, par2Vec3D);

            if (movingobjectposition != null) {
                return movingobjectposition;
            }
        }
        for (int l1 = 200; l1-- >= 0;) {
            if (Double.isNaN(par1Vec3D.xCoord) || Double.isNaN(par1Vec3D.yCoord) || Double.isNaN(par1Vec3D.zCoord)) {
                return null;
            }

            if (l == i && i1 == j && j1 == k) {
                return null;
            }
            boolean flag = true;
            boolean flag1 = true;
            boolean flag2 = true;
            double d = 999D;
            double d1 = 999D;
            double d2 = 999D;
            if (i > l) {
                d = l + 1.0D;
            } else if (i < l) {
                d = l + 0.0D;
            } else {
                flag = false;
            }

            if (j > i1) {
                d1 = i1 + 1.0D;
            } else if (j < i1) {
                d1 = i1 + 0.0D;
            } else {
                flag1 = false;
            }

            if (k > j1) {
                d2 = j1 + 1.0D;
            } else if (k < j1) {
                d2 = j1 + 0.0D;
            } else {
                flag2 = false;
            }
            double d3 = 999D;
            double d4 = 999D;
            double d5 = 999D;
            double d6 = par2Vec3D.xCoord - par1Vec3D.xCoord;
            double d7 = par2Vec3D.yCoord - par1Vec3D.yCoord;
            double d8 = par2Vec3D.zCoord - par1Vec3D.zCoord;

            if (flag) {
                d3 = (d - par1Vec3D.xCoord) / d6;
            }

            if (flag1) {
                d4 = (d1 - par1Vec3D.yCoord) / d7;
            }

            if (flag2) {
                d5 = (d2 - par1Vec3D.zCoord) / d8;
            }

            byte byte0 = 0;

            if (d3 < d4 && d3 < d5) {
                if (i > l) {
                    byte0 = 4;
                } else {
                    byte0 = 5;
                }

                par1Vec3D.xCoord = d;
                par1Vec3D.yCoord += d7 * d3;
                par1Vec3D.zCoord += d8 * d3;
            } else if (d4 < d5) {
                if (j > i1) {
                    byte0 = 0;
                } else {
                    byte0 = 1;
                }

                par1Vec3D.xCoord += d6 * d4;
                par1Vec3D.yCoord = d1;
                par1Vec3D.zCoord += d8 * d4;
            } else {
                if (k > j1) {
                    byte0 = 2;
                } else {
                    byte0 = 3;
                }

                par1Vec3D.xCoord += d6 * d5;
                par1Vec3D.yCoord += d7 * d5;
                par1Vec3D.zCoord = d2;
            }
            // was createVector
            Vec3 vec3d = Vec3.createVectorHelper(par1Vec3D.xCoord, par1Vec3D.yCoord, par1Vec3D.zCoord);
            l = (int) (vec3d.xCoord = MathHelper.floor_double(par1Vec3D.xCoord));

            if (byte0 == 5) {
                l--;
                vec3d.xCoord++;
            }

            i1 = (int) (vec3d.yCoord = MathHelper.floor_double(par1Vec3D.yCoord));

            if (byte0 == 1) {
                i1--;
                vec3d.yCoord++;
            }

            j1 = (int) (vec3d.zCoord = MathHelper.floor_double(par1Vec3D.zCoord));

            if (byte0 == 3) {
                j1--;
                vec3d.zCoord++;
            }

            Block j2 = world.getBlock(l, i1, j1);
            int k2 = world.getBlockMetadata(l, i1, j1);
           // Block block1 = Block.blocksList[j2];
            // previously ==null!
            if ((!par4 || j2 == null || j2.getCollisionBoundingBoxFromPool(world, l, i1, j1) != null) && j2 !=Block.getBlockById(0) && isBlockAllowable(j2, k2, par3, allowableBlocksType)) {
                MovingObjectPosition movingobjectposition1 = j2.collisionRayTrace(world, l, i1, j1, par1Vec3D, par2Vec3D);

                if (movingobjectposition1 != null) {
                    return movingobjectposition1;
                }
            }
        }
        return null;
    }
    //////////////////////////////////////////////////////////////////////////////
    public static boolean isBlockAllowable(Block block, int i2, boolean par3, int allowCrops) {
        boolean isAllowable = true;
        switch (allowCrops) {
            case 0:
                isAllowable = isSolidBlock(block, i2, par3);
                break;

        }
        return isAllowable;
    }
    ////////////////////////////////////////////////////////////////////////////
    public static boolean isSolidBlock(Block block, int par1, boolean par2) {
        if (block.canCollideCheck(par1, par2) && block.getMaterial().isSolid() && block.getMaterial().isOpaque()) {

            return true;
        }
        if (block.getMaterial() == Material.cactus || block.getMaterial() == Material.leaves || block == Block.getBlockFromName("glowstone"))
            return true;
        return false;
    }

}
