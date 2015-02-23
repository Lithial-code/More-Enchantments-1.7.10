package com.lithial.me.aencEx.api;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.enchantment.Enchantment;
import cpw.mods.fml.common.FMLLog;

public class IdExtension {
    public static Enchantment[] enchantmentsList = null;
    public static void expand() {
		FMLLog.fine("MoreEnchantments >> Expanding Enchantment Ids", new Object[0]);
		for (Field f : Enchantment.class.getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if (f.getName().equals("enchantmentsList") || f.getName().equals("field_77331_b")) {
					Field modfield = Field.class.getDeclaredField("modifiers");
					modfield.setAccessible(true);
					modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

					enchantmentsList = (Enchantment[]) f.get(null);
					final Enchantment[] newEnchantmentsList = new Enchantment[4096];
					System.arraycopy(enchantmentsList, 0, newEnchantmentsList, 0, enchantmentsList.length);
					f.set(enchantmentsList, newEnchantmentsList);
				}
			} catch (Exception e) {
				System.err.println("There was an error. Please report this to Lithial. Remember to include a copy of the crashlog in your report");
				System.err.println(e);
			}
		}

	}

}