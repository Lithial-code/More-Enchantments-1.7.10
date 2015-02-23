package com.lithial.me.handlers.utils;

import com.lithial.me.enchantments.Enchantments;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

/**
 * Created by Lithial on 10/02/2015.
 */
public class AntiVenomHandler {

    float residualAntiVenom = 0.0F;
    float venomCount = 0.0F;

    @SubscribeEvent
    public void onUpdate(LivingEvent.LivingUpdateEvent event)
    {
        if ((event.entityLiving instanceof EntityPlayer))
        {
            EntityPlayer  player = (EntityPlayer)event.entityLiving;
            if (player != null && Enchantments.allowAntiVenom) {
                updateAntiVenom(player);
            }
        }
    }

    @SubscribeEvent
    public void attackEntityFrom(LivingHurtEvent event)
    {
        EntityLivingBase ent = event.entityLiving;
        DamageSource damagesource = event.source;
        if ((ent instanceof EntityPlayer))
        {
            ent = event.entityLiving;
            if ((ent != null) && (
                    (event.source == DamageSource.magic) || (event.source.equals("indirectMagic")) || (event.source == DamageSource.wither) || (event.source.equals(Potion.poison))))
            {
                int venomProtect = Math.min((int)this.residualAntiVenom, (int)event.ammount);
                event.ammount = Math.max(event.ammount - venomProtect, 0);
                this.venomCount += 1.0F;
                this.residualAntiVenom -= (int)Math.floor(venomProtect);
            }
            event.ammount = Math.max(0, event.ammount);
        }
    }

    int antiVenomCount = 0;
    int durationMultiplerAntiVenom = 1;

    public void updateAntiVenom(EntityPlayer player)
    {
        this.antiVenomCount += 1;
        this.antiVenomCount = 0;
        this.residualAntiVenom = Math.max(0.0F, this.residualAntiVenom);
        float maxAntiVenom = 0.0F;
        maxAntiVenom += getEnchantmentLevelOnArmor(Enchantments.venom, player) * this.durationMultiplerAntiVenom;
        this.residualAntiVenom += maxAntiVenom / (48.0F * this.durationMultiplerAntiVenom);
        this.residualAntiVenom = Math.min(maxAntiVenom, this.residualAntiVenom);
    }

    public static int getEnchantmentLevelOnArmor(Enchantment e, EntityPlayer player)
    {
        int valToReturn = 0;
        for (int i = 0; i <3; i ++) {
            ItemStack armorItem = player.getCurrentArmor(i);
            valToReturn += getEnchantmentLevelOfItem(e, armorItem);
        }
        return valToReturn;
    }

    public static int getEnchantmentLevelOfItem(Enchantment e, ItemStack itemS)
    {
        return getEnchantmentLevelOfItem(e.effectId, itemS);
    }

    public static int getEnchantmentLevelOfItem(int effectId, ItemStack itemS)
    {
        if (itemS != null) {
            return EnchantmentHelper.getEnchantmentLevel(effectId, itemS);
        }
        return 0;
    }

}
