package com.arkprimalcahos.oreesp.gui;

import net.minecraft.client.gui.*;
import java.util.*;
import net.minecraft.block.Block;
import com.arkprimalcahos.oreesp.config.*;

public class GuiOreESP extends GuiScreen {

    private List<Block> list;

    public void initGui(){
        list = new ArrayList<>(OreConfig.ores.keySet());
        int y=20,i=0;
        for(Block b:list){
            this.buttonList.add(new GuiButton(i++,10,y,140,20,b.getLocalizedName()));
            y+=22;
        }
        this.buttonList.add(new GuiButton(999,160,20,60,20,"Save"));
    }

    protected void actionPerformed(GuiButton b){
        if(b.id==999){
            OreConfig.save();
            return;
        }
        Block ore = list.get(b.id);
        OreData d = OreConfig.ores.get(ore);
        d.r=(float)Math.random();
        d.g=(float)Math.random();
        d.b=(float)Math.random();
        d.enabled=!d.enabled;
    }

    public boolean doesGuiPauseGame(){return false;}
}
