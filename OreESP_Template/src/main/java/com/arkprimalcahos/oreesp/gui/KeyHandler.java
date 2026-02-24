package com.arkprimalcahos.oreesp.gui;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import com.arkprimalcahos.oreesp.OreESPMod;

public class KeyHandler {
    @SubscribeEvent
    public void key(InputEvent.KeyInputEvent e){
        if(OreESPMod.toggleKey.isPressed()) OreESPMod.enabled=!OreESPMod.enabled;
        if(OreESPMod.guiKey.isPressed()) Minecraft.getMinecraft().displayGuiScreen(new GuiOreESP());
    }
}
