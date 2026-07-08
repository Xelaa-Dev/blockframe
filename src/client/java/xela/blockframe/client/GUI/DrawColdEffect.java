package xela.blockframe.client.GUI;


import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import xela.blockframe.client.BlockFrameClient;


public class DrawColdEffect {
    public static void extract(GuiGraphicsExtractor graphics, DeltaTracker tickCounter) {

        //TODO: scale the image correctly
        if (BlockFrameClient.RENDER_COLD_HUD){
            Identifier texture = Identifier.withDefaultNamespace("textures/misc/powder_snow_outline.png");

            // renderLayer, texture, x, y, u, v, width, height, textureWidth, textureHeight
            graphics.blit(RenderPipelines.GUI_TEXTURED, texture, 0, 0, 0, 0, 1024,256 , 16, 16);
        }
    }
}