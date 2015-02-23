package com.lithial.me.handlers.controls;


import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.common.gameevent.TickEvent.Type;

public class KeyBind extends TKeyHandler {

    static KeyBinding jumpKey;
    static KeyBinding sneakKey;
   
    
    static Minecraft mc;

    public static boolean jumping;
    public static boolean sneaking;
    
    public KeyBind() {
        super(new KeyBinding[]{ }, new boolean[]{}, getVanillaKeyBindings(), new boolean[]{false, false});
    }

    private static KeyBinding[] getVanillaKeyBindings() {
        mc = Minecraft.getMinecraft();
        jumpKey = mc.gameSettings.keyBindJump;
        sneakKey = mc.gameSettings.keyBindSneak;
       
        return new KeyBinding[]{jumpKey, sneakKey};
    }
     
    @Override
    public void keyDown(Type types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
        if (kb == jumpKey)
            jumping = true;

        if (kb == sneakKey)
            sneaking = true;
        // System.out.println("Jumping =" + jumping);
    }



    @Override
    public void keyUp (Type types, KeyBinding kb, boolean tickEnd)
    {
        if(kb == jumpKey)
            jumping = false;
        if(kb == sneakKey)
            sneaking = false;
        // System.out.println("Jumping =" + jumping);
    }
}



