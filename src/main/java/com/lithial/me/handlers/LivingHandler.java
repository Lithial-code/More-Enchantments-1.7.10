package com.lithial.me.handlers;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import com.lithial.me.enchantments.EffectManager;
import com.lithial.me.enchantments.Enchantments;
import com.lithial.me.enchantments.Utils;
import com.lithial.me.handlers.controls.KeyBind;
import com.lithial.me.handlers.utils.AttributeManager;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class LivingHandler {
    public static Enchantments enchant;

    @SubscribeEvent
    public void HighJump(LivingJumpEvent event) {
        if (enchant.allowHighJump)
            if ((event.entity instanceof EntityPlayer)) {
                EntityPlayer player = (EntityPlayer) event.entity;
                if(EnchantmentHelper.getEnchantmentLevel(Enchantments.highjump.effectId, player.getCurrentArmor(0)) != 0 && KeyBind.sneaking && KeyBind.jumping)
                    player.motionY =0.41999998688697815D;
                if (EnchantmentHelper.getEnchantmentLevel(Enchantments.highjump.effectId, player.getCurrentArmor(0)) != 0 &&  !KeyBind.sneaking && KeyBind.jumping)
                    EffectManager.HighJump(player, player.getCurrentArmor(0));
            }
    }


    @SubscribeEvent
    public void AttributeEvent(LivingUpdateEvent event) {
        if ((event.entity instanceof EntityPlayer)) {
            EntityPlayer player = (EntityPlayer) event.entity;
            World world = player.worldObj;

            if (enchant.allowSwiftness) {
                if (EnchantmentHelper.getEnchantmentLevel(Enchantments.swiftness.effectId, player.getCurrentArmor(0)) != 0)
                    AttributeManager.Swiftness(player, player.getCurrentArmor(0));
                else AttributeManager.RemoveSwiftness(player, player.getCurrentArmor(0));
            }
            if (enchant.allowSteadFast)
                if (EnchantmentHelper.getEnchantmentLevel(Enchantments.steadfast.effectId, player.getCurrentArmor(0)) != 0)
                    AttributeManager.Steadfast(player);
                else AttributeManager.RemoveSteadfast(player);
        }

    }

    long time = 0;

    @SubscribeEvent
    public void LivingEvent(LivingUpdateEvent event) {
        time = event.entityLiving.worldObj.getWorldTime();
        if ((event.entity instanceof EntityPlayer)) {
            EntityPlayer player = (EntityPlayer) event.entity;
            World world = player.worldObj;
         //  int slots =  player.inventory.mainInventory.length;
           //System.out.println(slots);
            if (enchant.allowCloud)
                if (EnchantmentHelper.getEnchantmentLevel(Enchantments.cloud.effectId, player.getCurrentArmor(1)) != 0)
                    EffectManager.Cloud(player, player.getCurrentArmor(1));
       /*     if (EnchantmentHelper.getEnchantmentLevel(Enchantments.phantom.effectId, player.getCurrentArmor(0)) != 0)
                EffectManager.Phantom(player);*/
            for(int i = 0; i < 4; i++) {
                if (Utils.getEnchHelp(player, Enchantments.greenwood.effectId, player.getCurrentArmor(i)) != 0)
                    EffectManager.GreenWood(player, time);
                if(i == 4) {
                    if (Utils.getEnchHelp(player, Enchantments.greenwood.effectId, player.getCurrentEquippedItem()) != 0)
                        EffectManager.GreenWood(player, time);
                }
            }
            if (enchant.allowRegeneration)
                if (EnchantmentHelper.getEnchantmentLevel(Enchantments.regen.effectId, player.getCurrentArmor(2)) != 0 && player.isSneaking())
                    EffectManager.Regen(player, player.getCurrentArmor(2));
            if (enchant.allowHorticulture)
                if (EnchantmentHelper.getEnchantmentLevel(Enchantments.horticulture.effectId, player.getCurrentArmor(3)) != 0)
                    EffectManager.Horticulture(player, player.getCurrentArmor(3));
            if (enchant.allowMagnet)
                if (EnchantmentHelper.getEnchantmentLevel(Enchantments.magnet.effectId, player.getCurrentArmor(2)) != 0)
                    EffectManager.Magnet(player, world, player.getCurrentArmor(2));
            if (enchant.allowNightvision)
                if (EnchantmentHelper.getEnchantmentLevel(Enchantments.nightvision.effectId, player.getCurrentArmor(3)) != 0)
                    EffectManager.NightVision(player);
                else if (EnchantmentHelper.getEnchantmentLevel(Enchantments.nightvision.effectId, player.getCurrentArmor(3)) == 0)
                    EffectManager.RemoveNightVision(player);


            if (enchant.allowIceStep)
                if (EnchantmentHelper.getEnchantmentLevel(Enchantments.icestep.effectId, player.getCurrentArmor(0)) != 0)
                    EffectManager.IceStep(player);
            if (enchant.allowPhoto)
                if (EnchantmentHelper.getEnchantmentLevel(Enchantments.photo.effectId, player.getCurrentArmor(3)) != 0)
                    EffectManager.Photosynthesis(player, time);

        }

    }
}