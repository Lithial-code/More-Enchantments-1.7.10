package com.lithial.me;

import java.io.File;
import java.util.logging.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import com.lithial.me.aencEx.api.IdExtension;
import com.lithial.me.enchantments.Compat;
import com.lithial.me.enchantments.Enchantments;
import com.lithial.me.handlers.ArrowHandler;
import com.lithial.me.handlers.ConstructionHandler;
import com.lithial.me.handlers.DamageHandler;
import com.lithial.me.handlers.HarvestHandler;
import com.lithial.me.handlers.LivingHandler;
import com.lithial.me.handlers.controls.KeyBind;
import com.lithial.me.handlers.utils.AntiVenomHandler;
import com.lithial.me.network.ClientProxy;
import com.lithial.me.network.CommonProxy;
import com.lithial.me.utils.ModInfo;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;


@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.VERSION)


public class MoreEnchantments {
	@Mod.Instance(ModInfo.MOD_ID)
	static MoreEnchantments instance;

	@SidedProxy(clientSide = "com.lithial.me.network.ClientProxy", serverSide = "com.lithial.me.network.CommonProxy")
	public static CommonProxy proxy;
	static ClientProxy cproxy;
	static Logger log;
    public static Configuration config;
    public static Configuration config2;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		IdExtension.expand();
		File config = new File(event.getModConfigurationDirectory(), "MoreEnchantments/Core.cfg");
        File config2 = new File(event.getModConfigurationDirectory(), "MoreEnchantments/Compat.cfg");

		Enchantments.initialize(config);
        Compat.initialize(config2);
		Enchantments.save();
        Compat.save();

        if (event.getSide() == Side.CLIENT)
        {
        	 
            FMLCommonHandler.instance().bus().register(new KeyBind());
        }
 	}

	@EventHandler
	public void Initiate(FMLInitializationEvent Event) 
	{
		new Enchantments();
		proxy.registerEvents();

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new LivingHandler());
		MinecraftForge.EVENT_BUS.register(new DamageHandler());
		MinecraftForge.EVENT_BUS.register(new ArrowHandler());
		MinecraftForge.EVENT_BUS.register(new HarvestHandler());
		MinecraftForge.EVENT_BUS.register(new ConstructionHandler());
        MinecraftForge.EVENT_BUS.register(new AntiVenomHandler());
	}


}
