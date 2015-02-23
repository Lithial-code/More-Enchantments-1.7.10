package com.lithial.me.handlers.utils;

import java.util.UUID;

import com.lithial.me.enchantments.Utils;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.lithial.me.enchantments.Enchantments;

public class AttributeManager {
	public static UUID swiftid = UUID.fromString("254afd60-fde0-11e3-a3ac-0800200c9a66");
	public static UUID steadfastid = UUID.fromString("fc3b8e20-01b4-11e4-9191-0800200c9a66");
	
	
	public static void Swiftness(EntityPlayer player, ItemStack item)
	{ 	
		AttributeModifier mod;
		int haste = Utils.getEnchHelp(player,Enchantments.swiftness.effectId, player.getCurrentArmor(0));
		float bonus = haste * 0.12F;
		mod = new AttributeModifier(swiftid,"MySpeedModifier",bonus,2);
		IAttributeInstance atinst = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed); 
		{
			if(atinst.getModifier(swiftid) == null)
			{
				atinst.applyModifier(mod);
				player.jumpMovementFactor = (0.02F + (haste / 3) * 0.065F);
			}
		}
	}
	public static void RemoveSwiftness(EntityPlayer player, ItemStack item)
	{ 	
		AttributeModifier mod;
		int haste = Utils.getEnchHelp(player,Enchantments.swiftness.effectId, player.getCurrentArmor(0));
		float bonus = haste * 0.12F;
		mod = new AttributeModifier(swiftid,"MySpeedModifier",bonus,2);
		IAttributeInstance atinst = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed); 
		{
			if(atinst.getModifier(swiftid) != null)
			{
				atinst.removeModifier(mod);
				player.jumpMovementFactor = (0.02F);
			}
		}
	}
	public static void Steadfast(EntityPlayer player)
	{ 	
		AttributeModifier mod;	
		int steadfast = Utils.getEnchHelp(player,Enchantments.steadfast.effectId, player.getCurrentArmor(0));
		int bonus = (int) (steadfast);
		mod = new AttributeModifier(steadfastid,"MySteadfastModifier",bonus,2);
		IAttributeInstance atinst = player.getEntityAttribute(SharedMonsterAttributes.knockbackResistance); 
		{
			if(atinst.getModifier(steadfastid) == null)
			{
				atinst.applyModifier(mod);
		 
			}
		}
	}
	public static void RemoveSteadfast(EntityPlayer player)
	{ 	
		AttributeModifier mod;
		int steadfast = Utils.getEnchHelp(player,Enchantments.steadfast.effectId, player.getCurrentArmor(0));
		int bonus = (int) (steadfast * 0.334);
		mod = new AttributeModifier(steadfastid,"MySteadfastModifier",bonus,2);
		IAttributeInstance atinst = player.getEntityAttribute(SharedMonsterAttributes.knockbackResistance); 
		{
			if(atinst.getModifier(steadfastid) != null)
			{
				atinst.removeModifier(mod);
		 
			}
		}
	}
 
}

