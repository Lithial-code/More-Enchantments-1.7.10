package com.lithial.me.enchantments;


import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;


public class EnchantmentCore extends Enchantment {


	int maxLevel;
	int minLevel;
	Item item;
    Enchantment ban;
    Enchantment ban2;
    Enchantment ban3;
    Enchantment ban4;
    Enchantment banthis;
	/**
	 * @param id: ID for the enchantment being added.
	 * @param weight: How often the enchantment shows up.
	 * @param unlocalizedName: Name for the enchantment. (unlocalized)
	 * @param minLevel: The lowest possible level of enchantment.
	 * @param maxLevel: The highest possible level of enchantment.
	 */
    protected EnchantmentCore(int id, int weight,EnumEnchantmentType type, String unlocalizedName, int minLevel, int maxLevel, Enchantment par1, Enchantment par2,Enchantment par3, Enchantment par4) {

        super(id, weight, type);
        this.name = "me." + unlocalizedName;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.ban = par1;
        this.ban2 = par2;
        this.ban3 = par3;
        this.ban4 = par4;
        this.banthis = this;
    }

	public int getMinLevel() {

		return this.minLevel;
	}

	public int getMaxLevel() {

		return this.maxLevel;
	}


	public int getMinEnchantability(int par1) {

		return 10 + 20 * (par1 - 1);
	}

	public int getMaxEnchantability(int par1) {

		return super.getMinEnchantability(par1) + 50;
	}

    @Override
    public boolean canApplyTogether(Enchantment par1Enchantment)
    {

        if(ban != null && ban == par1Enchantment || ban2 !=null && ban2== par1Enchantment || ban3 !=null && ban3== par1Enchantment || ban4 !=null && ban4== par1Enchantment || banthis !=null && banthis== par1Enchantment)
            return !super.canApplyTogether(ban);
        else
            return true;

    }


    public String getTranslatedName(Enchantment enchantment,int par1,String newName) {
        String s = StatCollector.translateToLocal(getName());
        if (par1 > 1) {
            s = StatCollector.translateToLocal(newName);
        }
        return s;
    }
}
