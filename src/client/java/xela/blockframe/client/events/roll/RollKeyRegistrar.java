package xela.blockframe.client.events.roll;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;
import xela.blockframe.client.BlockFrameClient;
import xela.blockframe.client.events.doublejump.DoubleJumpRegistrar;
import xela.blockframe.networking.ChannelRegistrar;
import xela.blockframe.networking.payloads.records.MovementVectorPacket;


public class RollKeyRegistrar {


    public static final String KEY_ROLL = "key.blockframe.roll";
    public static KeyMapping Roll = KeyMappingHelper.registerKeyMapping(new KeyMapping(
            KEY_ROLL,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_LEFT_SHIFT,
            //Category can only be specified in 1 keybind file, else at startup it faisl to launch mc
            DoubleJumpRegistrar.CATEGORY
    ));

    private static int landingCooldown = 20;
    private static int ticksPassed = 0;
    private static boolean hasBeenPressed = false;

    public static void registerRollKeybind(){
        ClientTickEvents.END_CLIENT_TICK.register(client ->{
            processRollTickLogic(client);
        });
    }

    private static void processRollTickLogic(Minecraft client) {
        ticksPassed++;
        if (landingCooldown > 0){
            while(Roll.consumeClick());
            landingCooldown = landingCooldown - 1;
        }else if ( landingCooldown <= 0){
            while (Roll.consumeClick()) {
                if (hasBeenPressed && ticksPassed < 15 && client.player != null) {
                    landingCooldown = 20;
                    var pushVec = client.player.getLookAngle();
                    //Void the y movement
                    var finalPushVector = pushVec.add(BlockFrameClient.CONFIG.force_applied_on_movment()).add(0,-pushVec.y,0);
                    var typeof = "ROLL";
                    ChannelRegistrar.SERVERBOUND_CHANNEL.clientHandle().send(new MovementVectorPacket(finalPushVector, client.player.getStringUUID(),typeof));

                    ticksPassed = 0;
                    hasBeenPressed = false;
                }else {
                    ticksPassed = 0;
                    hasBeenPressed = true;
                    return;
                }
            }
        }
    }
}
