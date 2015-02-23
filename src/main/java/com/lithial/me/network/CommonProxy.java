package com.lithial.me.network;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.nbt.NBTTagCompound;

public class CommonProxy {
	private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();

	public CommonProxy() {
	}
    public boolean isClient()
    {
        return false;
    }
	public void registerEvents() {

    }

 

}
