package com.arkprimalcahos.oreesp;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.client.settings.KeyBinding;

@Mod(modid = "oreesp", name = "Ore ESP", version = "3.0")
public class OreESPMod {

    @SidedProxy(clientSide = "com.arkprimalcahos.oreesp.ClientProxy", serverSide = "com.arkprimalcahos.oreesp.CommonProxy")
    public static CommonProxy proxy;

    public static KeyBinding toggleKey;
    public static KeyBinding guiKey;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
}
