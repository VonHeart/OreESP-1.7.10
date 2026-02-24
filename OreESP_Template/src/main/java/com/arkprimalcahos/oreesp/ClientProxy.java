package com.arkprimalcahos.oreesp;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        OreESPMod.toggleKey = new KeyBinding("Toggle Ore ESP", Keyboard.KEY_O, "Ore ESP");
        OreESPMod.guiKey = new KeyBinding("Open Ore ESP GUI", Keyboard.KEY_P, "Ore ESP");
        ClientRegistry.registerKeyBinding(OreESPMod.toggleKey);
        ClientRegistry.registerKeyBinding(OreESPMod.guiKey);

        MinecraftForge.EVENT_BUS.register(new RenderESP());
        MinecraftForge.EVENT_BUS.register(new KeyHandler());
    }

    @Override
    public void init(FMLInitializationEvent event) {}
}
