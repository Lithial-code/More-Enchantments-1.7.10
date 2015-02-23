package com.lithial.me.handlers;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

import com.lithial.me.enchantments.Utils;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import com.lithial.me.enchantments.EffectManager;
import com.lithial.me.enchantments.Enchantments;


import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.Sys;

public class DamageHandler {
	public static Enchantments enchant;
	int slowArrow,poisonArrow,witherArrow,slowAoe,poisonAoe,witherAoe,slowAspect,poisonAspect,witherAspect;
	@SubscribeEvent
	public void onFall(LivingFallEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if (enchant.allowHighJump) {
				if (Utils.getEnchHelp(player, Enchantments.highjump.effectId, player.getCurrentArmor(0)) != 0)
					event.distance /= Utils.getEnchHelp(player, Enchantments.highjump.effectId, player.getCurrentArmor(0));
			}
			if (enchant.allowCloud) {
				if (Utils.getEnchHelp(player, Enchantments.cloud.effectId, player.getCurrentArmor(1)) != 0)
					event.distance = 0;
			}
		}
	}

	@SubscribeEvent
	public void onDeath(LivingDeathEvent event) {
		Random rand = new Random();

		if (!(event.source instanceof EntityDamageSource)) return;

		final EntityDamageSource eds = (EntityDamageSource) event.source;
		final Entity attacker = eds.getSourceOfDamage();
		EntityLivingBase living = (EntityLivingBase) event.entityLiving;

		if (attacker instanceof EntityPlayer) {

			if (enchant.allowWisdom) {
				int wisdom = Utils.getEnchHelp((EntityPlayer) attacker, Enchantments.wisdom.effectId, ((EntityPlayer) attacker).getHeldItem());
				if (wisdom != 0) {
					if (!living.worldObj.isRemote) {
						int exp = rand.nextInt((wisdom * 2) +1);
						living.worldObj.spawnEntityInWorld(new EntityXPOrb(living.worldObj, living.posX, living.posY, living.posZ, rand.nextInt(wisdom)));

					}
				}
				if (enchant.allowLeech) {
					int leech = Utils.getEnchHelp((EntityPlayer) attacker, Enchantments.leech.effectId, ((EntityPlayer) attacker).getHeldItem());
					if (leech != 0) {
						if (Math.random() * 10 < 2)
							((EntityPlayer) attacker).heal(leech);
					}
				}
			}
		}
	}

	/*   @SubscribeEvent
    public void Parry(LivingHurtEvent event) {
        Entity reflectedArrow = null;
        float reflectedDamage = 0.0F;

        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;


            int parry = Utils.getEnchHelp(player, Enchantments.parry.effectId, player.getHeldItem());
            if (parry != 0) {

                if (player.isBlocking()) {
                    System.out.println("isBlocking");
                    event.ammount -= (1 * (parry + 1));
                    reflectedDamage = event.ammount;

                    if (event.source.isProjectile()) {
                        Entity strikingArrow = event.source.getSourceOfDamage();
                        reflectedArrow = strikingArrow;
                        Vec3 vec3 = player.getLookVec();
                        if (vec3 != null) {
                            strikingArrow.motionX = vec3.xCoord;
                            strikingArrow.motionY = vec3.yCoord;
                            strikingArrow.motionZ = vec3.zCoord;
                            if (player.worldObj.isRemote) {
                                strikingArrow.setVelocity(strikingArrow.motionX * 0.1D, strikingArrow.motionY * 0.1D, strikingArrow.motionZ * 0.1D);
                                System.out.println("arrow deflected");
                            }
                        }
                    }
                }
            }

            if (event.entityLiving instanceof EntityLiving) {
                EntityLivingBase living = event.entityLiving;

                 if (event.source.isProjectile()) {
                    if(reflectedArrow != null)
                        if (event.source.getEntity() == reflectedArrow) {
                            event.ammount = reflectedDamage;
                            System.out.println(living.getHealth());
                        }

                }
            }
        }
    }*/


	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		if (event.entityLiving instanceof EntityLiving)
		{

			if (event.source.getEntity() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) event.source.getEntity();
				EntityLiving living = (EntityLiving) event.entityLiving;
				World world = player.worldObj;
				Long time = event.entityLiving.worldObj.getWorldTime();

				if(player instanceof EntityPlayer)
					if(Enchantments.allowMulti) {
						int multi = Utils.getEnchHelp(player, Enchantments.multipurpose.effectId, player.getHeldItem());

						//     player.addChatMessage( new ChatComponentText("player did " + event.ammount));
						if (multi > 0) {
							event.ammount += (multi * 1.5);
							//  System.out.println(event.ammount);
							//   player.addChatMessage( new ChatComponentText("bonus damage  " + event.ammount));
						}
						if(Enchantments.allowDisarm)
							if (Utils.getEnchHelp(player, Enchantments.disarm.effectId, player.getHeldItem()) !=0) {
								if (Math.random() * 100 <= 5 * Utils.getEnchHelp(player, Enchantments.disarm.effectId, player.getHeldItem())) 
									EffectManager.Disarm(living);
							}


					}



				if(event.source.isProjectile())
				{
					if(enchant.allowSlowArrow){
						if (Utils.getEnchHelp(player,Enchantments.slowArrow.effectId,player.getHeldItem()) != 0){
							slowArrow = Utils.getEnchHelp(player,Enchantments.slowArrow.effectId,player.getCurrentEquippedItem());
						EffectManager.Arrow(living, player.getHeldItem(), Potion.moveSlowdown.id, (Enchantments.slowArrowTimer*20), slowArrow);
					}
					}
					if(enchant.allowPoisonArrow){
						if (Utils.getEnchHelp(player,Enchantments.poisonArrow.effectId,player.getHeldItem()) != 0){
							poisonArrow = Utils.getEnchHelp(player,Enchantments.poisonArrow.effectId,player.getCurrentEquippedItem());
						EffectManager.Arrow(living, player.getHeldItem(), Potion.poison.id, (Enchantments.poisonArrowTimer*20), poisonArrow);
					}
					}
					if(enchant.allowWitherArrow){
						if (Utils.getEnchHelp(player,Enchantments.witherArrow.effectId,player.getHeldItem()) != 0){
							witherArrow = Utils.getEnchHelp(player,Enchantments.witherArrow.effectId,player.getCurrentEquippedItem());
						EffectManager.Arrow(living, player.getHeldItem(), Potion.wither.id, (Enchantments.witherArrowTimer*20), witherArrow);
					}
					}
					if(enchant.allowSlowAoe){
						if (Utils.getEnchHelp(player,Enchantments.slowAoe.effectId,player.getHeldItem()) != 0){
							slowAoe= Utils.getEnchHelp(player,Enchantments.slowAoe.effectId,player.getCurrentEquippedItem());
						EffectManager.Arrow(living, player.getHeldItem(), Potion.moveSlowdown.id, (Enchantments.slowAOETimer*20), slowAoe);
					}
					}
					if(enchant.allowPoisonAoe){
						if (Utils.getEnchHelp(player,Enchantments.poisonAoe.effectId,player.getHeldItem()) != 0){
							poisonAoe= Utils.getEnchHelp(player,Enchantments.poisonAoe.effectId,player.getCurrentEquippedItem());
						EffectManager.Arrow(living, player.getHeldItem(), Potion.poison.id, (Enchantments.poisonArrowTimer*20), poisonAoe);
					}
					}
					if(enchant.allowWitherAOE){
						if (Utils.getEnchHelp(player,Enchantments.witherAoe.effectId,player.getHeldItem()) != 0){
							witherAoe = Utils.getEnchHelp(player,Enchantments.witherAoe.effectId,player.getCurrentEquippedItem());
						EffectManager.Arrow(living, player.getHeldItem(), Potion.wither.id, (Enchantments.witherAOETimer*20), witherAoe);
					}
					}
				}

				if(enchant.allowVertically){
					if(Utils.getEnchHelp(player,Enchantments.verticallity.effectId,player.getHeldItem()) != 0)
						event.entityLiving.moveEntity(0, Enchantments.verticallitybonus * Utils.getEnchHelp(player,Enchantments.verticallity.effectId,player.getHeldItem()), 0);
				}
				if(enchant.allowLethargy){
					if (Utils.getEnchHelp(player,Enchantments.slowAspect.effectId,player.getHeldItem()) != 0){
						slowAspect= Utils.getEnchHelp(player,Enchantments.slowAspect.effectId,player.getCurrentEquippedItem());
					EffectManager.Arrow(living, player.getHeldItem(), Potion.moveSlowdown.id, (Enchantments.slowAspectTimer*20),slowAspect);
				}
				}
				if(enchant.allowPoisonAspect){
					if (Utils.getEnchHelp(player,Enchantments.poisonAspect.effectId,player.getHeldItem()) != 0){
						poisonAspect= Utils.getEnchHelp(player,Enchantments.poisonAspect.effectId,player.getCurrentEquippedItem());
					EffectManager.Arrow(living, player.getHeldItem(), Potion.poison.id, (Enchantments.poisonAspectTimer*20),poisonAspect);
				}
				}
				if(enchant.allowWitherAspect){
					if (Utils.getEnchHelp(player,Enchantments.witherAspect.effectId,player.getHeldItem()) != 0){
						witherAspect= Utils.getEnchHelp(player,Enchantments.witherAspect.effectId,player.getCurrentEquippedItem());
					EffectManager.Arrow(living, player.getHeldItem(), Potion.wither.id, (Enchantments.witherAspectTimer*20), witherAspect);
				}
				}
				if(event.source.isProjectile()){

					Entity strikingArrow = event.source.getSourceOfDamage();
					{
						if(enchant.allowSlowAoe||enchant.allowPoisonAoe||enchant.allowWitherAOE){
							if(Utils.getEnchHelp(player,Enchantments.slowAoe.effectId,player.getHeldItem()) != 0 || (Utils.getEnchHelp(player,Enchantments.poisonAoe.effectId,player.getHeldItem()) != 0|| (Utils.getEnchHelp(player,Enchantments.witherAoe.effectId,player.getHeldItem()) != 0)))
							{
								int level = 0;
								float splashDamage = event.ammount * (level * 0.13F);


								AxisAlignedBB boundBox = AxisAlignedBB.getBoundingBox(strikingArrow.posX-3, strikingArrow.posY-3, strikingArrow.posZ-3, strikingArrow.posX+3, strikingArrow.posY+3, strikingArrow.posZ+3);
								ArrayList<Entity> targetEntities = new ArrayList<Entity>(strikingArrow.worldObj.getEntitiesWithinAABBExcludingEntity(event.entity, boundBox));
								ListIterator<Entity> itr = targetEntities.listIterator();
								while(itr.hasNext())
								{
									Entity target = itr.next();
									if(target instanceof EntityLivingBase)
									{
										if(enchant.allowSlowAoe){
											if(Utils.getEnchHelp(player,Enchantments.slowAoe.effectId,player.getHeldItem()) != 0)
											{
												slowAoe= Utils.getEnchHelp(player,Enchantments.slowAoe.effectId,player.getCurrentEquippedItem());
												((EntityLivingBase) target).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, (Enchantments.slowAOETimer*20), slowAoe));
												target.attackEntityFrom(DamageSource.magic, splashDamage);
											}
										}
										if(enchant.allowPoisonAoe){
											if(Utils.getEnchHelp(player,Enchantments.poisonAoe.effectId,player.getHeldItem()) != 0)
											{
												poisonAoe= Utils.getEnchHelp(player,Enchantments.poisonAoe.effectId,player.getCurrentEquippedItem());
												((EntityLivingBase) target).addPotionEffect(new PotionEffect(Potion.poison.id, (Enchantments.poisonAOETimer*20), poisonAoe));
												target.attackEntityFrom(DamageSource.magic, splashDamage);
											}
										}
										if(enchant.allowWitherAOE){
											if(Utils.getEnchHelp(player,Enchantments.witherAoe.effectId,player.getHeldItem()) != 0)
											{
												witherAoe= Utils.getEnchHelp(player,Enchantments.witherAoe.effectId,player.getCurrentEquippedItem());
												((EntityLivingBase)target).addPotionEffect(new PotionEffect(Potion.wither.id, (Enchantments.witherAOETimer *20), witherAoe));
												target.attackEntityFrom(DamageSource.magic, splashDamage);
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

	}

	@SubscribeEvent
	public void onAttack(LivingAttackEvent event) {
		{
			if (!(event.source instanceof EntityDamageSource))return;
			final EntityDamageSource eds = (EntityDamageSource) event.source;
			final Entity attacker = eds.getSourceOfDamage();
			/////////////////////
			//players with leech
			/////////////////////

			if(enchant.allowLeech){
				if (attacker instanceof EntityPlayer)
				{
					final int leech = Utils.getEnchHelp((EntityPlayer)attacker,Enchantments.leech.effectId, ((EntityPlayer)attacker).getHeldItem());
					if (leech > 0 && event.ammount > 5) {

						int foodlevel = Utils.getFood((EntityPlayer)attacker);//((EntityPlayer)attacker).getFoodStats().getFoodLevel();
						float foodsat = Utils.getSat((EntityPlayer)attacker);
						if (foodlevel < 20 && foodsat <= 20F) {
							int foodHeal = foodlevel + leech;
							float foodsat1 = foodsat + leech;
							if (Math.random() * 10 <= 1) {
								Utils.setFood((EntityPlayer)attacker,foodHeal,foodsat1);
								//	((EntityPlayer)attacker).getFoodStats().addStats(foodHeal , foodsat1);

							}
						}
						if (foodlevel == 20) {
							if (Math.random() * 10 <= 1) {
								((EntityPlayer)attacker).heal(leech);
							}
						}
					}
				}

				/////////////////
				//mobs with leech
				/////////////////
				if(attacker instanceof EntityLivingBase)
				{
					final int leech = EnchantmentHelper.getEnchantmentLevel(Enchantments.leech.effectId, ((EntityLivingBase)attacker).getHeldItem());
					if(leech != 0)
						((EntityLivingBase)attacker).heal(leech);
				}
			}
		}
	}
}