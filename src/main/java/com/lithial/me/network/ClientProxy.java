package com.lithial.me.network;

import com.lithial.me.handlers.controls.KeyBind;

import cpw.mods.fml.common.FMLCommonHandler;



public class ClientProxy extends CommonProxy {
    @Override
    public boolean isClient()
    {
        return true;
    }
    @Override
	public void registerEvents() 
	{
        super.registerEvents();
/*		KeyBinding[] key = {new KeyBinding("Cloud", Keyboard.KEY_V)};
		boolean[] repeat = {false};
		KeyBindingRegistry.registerKeyBinding(new KeyBind(key, repeat));*/
		FMLCommonHandler.instance().bus().register(new KeyBind());
	}


}
