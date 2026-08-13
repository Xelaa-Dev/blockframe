package xela.blockframe.client.GUI;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import xela.blockframe.client.BlockFrameClient;

public class DrawColdEffect {
    private static final Identifier POWDER_SNOW_OUTLINE = Identifier.withDefaultNamespace("textures/misc/powder_snow_outline.png");

    public static void extract(GuiGraphicsExtractor graphics, DeltaTracker tickCounter) {
        if (BlockFrameClient.RENDER_COLD_HUD) {
            Minecraft client = Minecraft.getInstance();
            int screenWidth = client.getWindow().getGuiScaledWidth();
            int screenHeight = client.getWindow().getGuiScaledHeight();

            graphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    POWDER_SNOW_OUTLINE,
                    0, 0,                      // Screen origin
                    0.0f, 0.0f,                // UV offset
                    screenWidth, screenHeight, // Total screen size
                    256, 256,                  // Source UV map size
                    256, 256                   // Source texture size
            );
        }
    }
}