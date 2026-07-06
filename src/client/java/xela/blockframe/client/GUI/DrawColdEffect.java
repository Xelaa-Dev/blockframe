package xela.blockframe.client.GUI;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.util.Util;
import xela.blockframe.BlockFrame;

public class DrawColdEffect {
    public static void extract(GuiGraphicsExtractor graphics, DeltaTracker tickCounter) {
        int color = 0xFFFF0000; // Red
        int targetColor = 0xFF00FF00; // Green

        // You can use the Util.getMillis() function to get the current time in milliseconds.
        // Divide by 1000 to get seconds.
        double currentTime = Util.getMillis() / 1000.0;

        // "lerp" simply means "linear interpolation", which is a fancy way of saying "blend".
        float lerpedAmount = Mth.abs(Mth.sin((float) currentTime));
        int lerpedColor = ARGB.linearLerp(lerpedAmount, color, targetColor);

        Identifier texture = Identifier.withDefaultNamespace("textures/misc/powder_snow_outline.png");
        // renderLayer, texture, x, y, u, v, width, height, textureWidth, textureHeight
        graphics.blit(RenderPipelines.GUI_TEXTURED, texture, 0, 0, 0, 0, 1024, 256, 256, 256);


        // Draw a square with the lerped color.
        // x1, x2, y1, y2, color
        //graphics.fill(0, 0, 10, 10, lerpedColor);
    }
}
