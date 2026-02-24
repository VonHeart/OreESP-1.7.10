package com.arkprimalcahos.oreesp.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import java.io.File;
import java.util.*;

public class OreConfig {

    public static Map<Block, OreData> ores = new HashMap<>();
    public static Configuration cfg;

    public static void load() {
        cfg = new Configuration(new File("config/OreESP.cfg"));
        cfg.load();
        ores.clear();

        for (String name : OreDictionary.getOreNames()) {
            if (!name.toLowerCase().contains("ore")) continue;
            for (ItemStack stack : OreDictionary.getOres(name)) {
                Block b = Block.getBlockFromItem(stack.getItem());
                if (b == null) continue;
                String key = b.getUnlocalizedName();
                boolean enabled = cfg.get(key, "enabled", true).getBoolean();
                float r = (float)cfg.get(key, "r", 1f).getDouble();
                float g = (float)cfg.get(key, "g", 1f).getDouble();
                float bl = (float)cfg.get(key, "b", 1f).getDouble();
                ores.put(b, new OreData(enabled,r,g,bl));
            }
        }
        cfg.save();
    }

    public static void save() {
        for (Map.Entry<Block, OreData> e : ores.entrySet()) {
            String key = e.getKey().getUnlocalizedName();
            OreData d = e.getValue();
            cfg.get(key,"enabled",d.enabled).set(d.enabled);
            cfg.get(key,"r",d.r).set(d.r);
            cfg.get(key,"g",d.g).set(d.g);
            cfg.get(key,"b",d.b).set(d.b);
        }
        cfg.save();
    }
}
