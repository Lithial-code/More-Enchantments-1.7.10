package com.lithial.me.handlers;

import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;

import com.lithial.me.enchantments.Enchantments;
import com.lithial.me.enchantments.Utils;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class HarvestHandler {
	public static Enchantments enchant;
	@SubscribeEvent
	public void onUpdate(PlayerEvent.BreakSpeed event) {
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if(enchant.allowVigour){
				int vigor = Utils.getEnchHelp(player,Enchantments.vigor.effectId, player.getHeldItem());
				if (vigor > 0)  
					event.newSpeed += (2.0F * vigor);
			}
		}
	}
	@SubscribeEvent
	public void onBlockBreak(HarvestDropsEvent event) {
		if(enchant.allowHeat){
			if (event.harvester != null) {
				//When a block is broken by a player (or similar)...
				ItemStack itemHeld = event.harvester.inventory.getCurrentItem();
				//We check what they're holding.
				if (itemHeld != null) {
					//If what they're holding has the Super Heat enchant on it...
					Map enchants = EnchantmentHelper.getEnchantments(itemHeld);
					if (enchants.get(Enchantments.heat.effectId) != null) {
						Random random = new Random();
						int fortune = EnchantmentHelper.getFortuneModifier(event.harvester);
						//then we check Forge's list of smelting recipes and see if there's one for the broken block.

						if(event.drops.size()!= 0){
							int amount = event.drops.size(); 
							System.out.print(amount);
							for(int z = 0; z < event.drops.size(); z ++){
								ItemStack smeltResult = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(event.drops.get(z).getItem()));
								if (smeltResult != null) {

									//If it has a recipe we adjust the blocks drops (made possible by the HarvestDropsEvent)
									//to be what you get from smelting.
									//System.out.println(smeltResult); //Debugging

									//Default drop amount
									System.out.print(amount);
									int meta = smeltResult.getItem().getDamage(smeltResult); //Metadata
									Item smeltDropID = smeltResult.getItem(); //Dropped ID

									if (fortune > 0) {
										amount *= (random.nextInt(fortune + 1) + 1); //Adjusting for Fortune
									}
									ItemStack dropStack = ItemStack.copyItemStack(smeltResult); //New ItemStack, identical to smeltResult
									event.drops.clear(); //Clear the blocks drops
									for(int x = 0; x < amount; x++)
										event.drops.add(dropStack); //And replace them with our new smelted ItemStack
									//Then we drop the appropriate amount of XP you'd get from smelting in a furnace...
									int i = dropStack.stackSize;
									float xp = FurnaceRecipes.smelting().func_151398_b(dropStack);
									int j;
									if (xp == 0.0f) {
										i = 0;
									} else if (xp < 1.0f) {
										j = MathHelper.floor_float((float) i * xp);
										if (j < MathHelper.ceiling_float_int((float) i * xp) && (float) Math.random() < (float) i * xp - (float) j) {
											++j;
										}
										i = j;
									}
									//And drop it.
									while (i > 0) {
										j = EntityXPOrb.getXPSplit(i);
										i -= j;
										event.world.spawnEntityInWorld(new EntityXPOrb(event.world, event.x, event.y + 0.5, event.z, j));
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
