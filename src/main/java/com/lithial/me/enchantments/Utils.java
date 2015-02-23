package com.lithial.me.enchantments;


import ibxm.Player;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.MathHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lithial on 9/02/2015.
 */
public class Utils {

    public static int getFood(EntityPlayer player)
    {
        return player.getFoodStats().getFoodLevel();
    }
    public static float getSat(EntityPlayer player)
    {
        return player.getFoodStats().getSaturationLevel();
    }
    public static void setFood(EntityPlayer player, int val1, float val2)
    {
        player.getFoodStats().addStats(val1,val2);
    }
    public static int getEnchHelp(EntityPlayer player, int EffectId, ItemStack Armor)
    {
        return EnchantmentHelper.getEnchantmentLevel(EffectId, Armor);
    }
    public static boolean seeSky (EntityPlayer player)
    {
        if(player.worldObj.canBlockSeeTheSky(MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posY), MathHelper.floor_double(player.posZ)))
            return true;
        else
            return false;
    }
    public static ItemStack[] getTools(EntityPlayer player)
    {
        List<ItemStack> tools = new ArrayList();
        for (int i = 0; i < (Enchantments.hotbarOnly ? 9 : 36); i++)
        {
            ItemStack stack = player.inventory.getStackInSlot(i);
            if ((stack != null) && (stack.getItem() instanceof ItemTool || stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemBow)) {
                tools.add(stack);
            }
        }
        return (ItemStack[])tools.toArray(new ItemStack[tools.size()]);
    }
}
