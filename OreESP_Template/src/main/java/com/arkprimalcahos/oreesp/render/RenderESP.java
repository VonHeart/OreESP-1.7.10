package com.arkprimalcahos.oreesp.render;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;
import com.arkprimalcahos.oreesp.OreESPMod;
import com.arkprimalcahos.oreesp.config.*;

public class RenderESP {

    @SubscribeEvent
    public void render(RenderWorldLastEvent e) {
        if (!OreESPMod.enabled) return;
        Minecraft mc = Minecraft.getMinecraft();

        double px = mc.thePlayer.lastTickPosX + (mc.thePlayer.posX - mc.thePlayer.lastTickPosX) * e.partialTicks;
        double py = mc.thePlayer.lastTickPosY + (mc.thePlayer.posY - mc.thePlayer.lastTickPosY) * e.partialTicks;
        double pz = mc.thePlayer.lastTickPosZ + (mc.thePlayer.posZ - mc.thePlayer.lastTickPosZ) * e.partialTicks;

        GL11.glPushMatrix();
        GL11.glTranslated(-px, -py, -pz);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(2f);

        int r = 32;
        for (int x=(int)mc.thePlayer.posX-r;x<mc.thePlayer.posX+r;x++)
            for (int y=(int)mc.thePlayer.posY-r;y<mc.thePlayer.posY+r;y++)
                for (int z=(int)mc.thePlayer.posZ-r;z<mc.thePlayer.posZ+r;z++) {
                    Block b = mc.theWorld.getBlock(x,y,z);
                    if (!OreConfig.ores.containsKey(b)) continue;
                    OreData d = OreConfig.ores.get(b);
                    if (!d.enabled) continue;
                    AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(x,y,z,x+1,y+1,z+1);
                    GL11.glColor4f(d.r,d.g,d.b,0.8f);
                    drawBox(bb);
                }

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }

    private void drawBox(AxisAlignedBB bb){
        GL11.glBegin(GL11.GL_LINES);
        double x1=bb.minX,y1=bb.minY,z1=bb.minZ,x2=bb.maxX,y2=bb.maxY,z2=bb.maxZ;
        GL11.glVertex3d(x1,y1,z1);GL11.glVertex3d(x2,y1,z1);
        GL11.glVertex3d(x2,y1,z1);GL11.glVertex3d(x2,y2,z1);
        GL11.glVertex3d(x2,y2,z1);GL11.glVertex3d(x1,y2,z1);
        GL11.glVertex3d(x1,y2,z1);GL11.glVertex3d(x1,y1,z1);
        GL11.glVertex3d(x1,y1,z2);GL11.glVertex3d(x2,y1,z2);
        GL11.glVertex3d(x2,y1,z2);GL11.glVertex3d(x2,y2,z2);
        GL11.glVertex3d(x2,y2,z2);GL11.glVertex3d(x1,y2,z2);
        GL11.glVertex3d(x1,y2,z2);GL11.glVertex3d(x1,y1,z2);
        GL11.glEnd();
    }
}
