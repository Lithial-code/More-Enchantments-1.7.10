package com.lithial.me.handlers;

import com.lithial.me.enchantments.Enchantments;
import com.lithial.me.enchantments.Utils;
import com.lithial.me.handlers.utils.AttributeManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.DataWatcher.WatchableObject;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;

import java.util.List;

public class ConstructionHandler {
    public static Enchantments enchant;
    public static int explode = 8;
    @SubscribeEvent
    public void joinWorld(EntityJoinWorldEvent event)
    {

        if ((event.entity instanceof EntityPlayer)) {
            EntityPlayer player = (EntityPlayer)event.entity;
          
            if(enchant.allowSwiftness){
                if (Utils.getEnchHelp(player, Enchantments.swiftness.effectId, player.getCurrentArmor(0)) != 0)
                    AttributeManager.Swiftness(player, player.getCurrentArmor(0));
            }
            if(enchant.allowNightvision){
                if(Utils.getEnchHelp(player,Enchantments.nightvision.effectId, player.getCurrentArmor(3)) != 0)
                    player.removePotionEffect(16);
            }
        
			
        }
    }

    @SubscribeEvent
    public void HandleArrowSpawn(EntityJoinWorldEvent event)
    {
        if(enchant.allowBlast)

        {
            if(!(event.entity instanceof EntityArrow))
                return;

            if(!(((EntityArrow)event.entity).shootingEntity instanceof EntityLivingBase))
                return;

            EntityLivingBase shooter = (EntityLivingBase)((EntityArrow)event.entity).shootingEntity;
            ItemStack itemBow = shooter.getHeldItem();

            if(itemBow == null)
                return;

            if(EnchantmentHelper.getEnchantmentLevel(Enchantments.blast.effectId, itemBow) <= 0)
                return;

            boolean bitsafe = false;
            @SuppressWarnings("unchecked")
            List<WatchableObject> watched = event.entity.getDataWatcher().getAllWatched();
            for(WatchableObject obj : watched)
            {
                if(obj.getDataValueId() == 24)
                {
                    bitsafe = true;
                    break;
                }
            }
            if(!bitsafe)
                event.entity.getDataWatcher().addObject(24, Integer.valueOf(0));

            event.entity.getDataWatcher().updateObject(24, event.entity.getDataWatcher().getWatchableObjectInt(24) + Integer.valueOf(explode));
        }
    }
    @SubscribeEvent
    public void HandleEnchant(PlaySoundAtEntityEvent event)
    {
        if(enchant.allowBlast)
        {
            if(!(event.entity instanceof EntityArrow))
                return;

            if(event.name != "random.bowhit")
                return;

            EntityArrow strikingArrow = ((EntityArrow)event.entity);
            boolean bitsafe = false;
            @SuppressWarnings("unchecked")
            List<WatchableObject> watched = strikingArrow.getDataWatcher().getAllWatched();
            for(WatchableObject obj : watched)
            {
                if(obj.getDataValueId() == 24)
                {
                    bitsafe = true;
                    break;
                }
            }
            if(!bitsafe)
                return;
            int infoBits = strikingArrow.getDataWatcher().getWatchableObjectInt(24);
            if((infoBits & explode) != 0)
            {
                strikingArrow.worldObj.newExplosion(strikingArrow, strikingArrow.posX, strikingArrow.posY, strikingArrow.posZ, 2.0F, false, false);
                strikingArrow.setDead();
            }
        }
    }
}

