package xela.blockframe.client.events.doublejump;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;
import org.lwjgl.glfw.GLFW;
import xela.blockframe.BlockFrame;
import xela.blockframe.client.BlockFrameClient;
import xela.blockframe.networking.ChannelRegistrar;
import xela.blockframe.networking.payloads.records.GenericStringMessagePacket;
import xela.blockframe.networking.payloads.records.MovementVectorPacket;

/*
This function will be heavily commented for the sake of my sanity and to understand better how it all works,
i did rewrite most of it myself but i still had AI help so i need to document what it did
 */

public class DoubleJumpRegistrar {
    public static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(
            Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID, "blockframe")
    );
    public static final String KEY_DOUBLE_JUMP = "key.blockframe.double_jump";
    public static KeyMapping doubleJump = KeyMappingHelper.registerKeyMapping(new KeyMapping(
            KEY_DOUBLE_JUMP,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_SPACE,
            CATEGORY
    ));

    public static boolean fallingFromDoubleJump = false;
    private static boolean wasOnGround = true;
    private static int landingCooldown = 0;

    private enum JumpState {
        IDLE,
        FIRST_PRESS,
        MUST_RELEASE
    }

    private static JumpState jumpState = JumpState.IDLE;

    //Yea i used claude for this i couldn't figure it out :(
    public static void registerDoubleJumpKeybind() {
        ClientTickEvents.END_CLIENT_TICK.register(DoubleJumpRegistrar::processDoubleJumpTickLogic);
    }


    private static void processDoubleJumpTickLogic(Minecraft client) {
        //Necessary checks since if we swim up we will eventually go to the surface and accept the double jump since we kept space pressed
        if (client.player != null && (!client.player.isInShallowWater() && !client.player.isInWater() && !client.player.isInLava())) {

        /*
        Check every tick where we are, if we weren't on ground
        and now we are, we need to wait before applying any double jump logic
        */
            boolean isOnGround = client.player.onGround();
            boolean justLanded = !wasOnGround && isOnGround;

        /*
        If we just landed, we should reset all the flags to make the double jump voids inputs and
        wait a couple ticks
         */
            if (justLanded) {
                jumpState = JumpState.IDLE;
                landingCooldown = 2;
                while (DoubleJumpRegistrar.doubleJump.consumeClick()) ;
                wasOnGround = true;
                return;
            }

            //we store what happened this tick to check what happens in the next
            wasOnGround = isOnGround;

        /*
        Actually delay the double jump logic
         */
            if (landingCooldown > 0) {
                landingCooldown--;
                while (DoubleJumpRegistrar.doubleJump.consumeClick()) ;
                return;
            }

            boolean isInputDown = doubleJump.isDown();

            boolean hadClick = false;
            while (doubleJump.consumeClick()) {
                hadClick = true;
            }

            if (isOnGround) {
                jumpState = JumpState.IDLE;
                return;
            }

            switch (jumpState) {
                case IDLE:
                    if (hadClick) {
                        jumpState = JumpState.FIRST_PRESS;
                    }
                    break;
                case FIRST_PRESS:
                    if (!isInputDown) {
                        jumpState = JumpState.MUST_RELEASE;
                    }
                    break;
                case MUST_RELEASE:
                    if (hadClick) {
                        Vec3 finalPushVector;
                        if (client.player.getDeltaMovement().x == 0 && client.player.getDeltaMovement().z == 0) {
                            finalPushVector = new Vec3(0, BlockFrameClient.CONFIG.force_applied_on_movment(), 0);
                        } else {
                            var pushVec = client.player.getLookAngle();
                            finalPushVector = pushVec.add(0, BlockFrameClient.CONFIG.force_applied_on_movment(), 0);
                        }
                        var typeof = "DOUBLE_JUMP";
                        ChannelRegistrar.NET_CHANNEL.clientHandle().send(new MovementVectorPacket(finalPushVector,
                                client.player.getStringUUID(), typeof));

                        ChannelRegistrar.NET_CHANNEL.clientHandle().send(new GenericStringMessagePacket(
                                "FALL_DAMAGE_DISABLE", client.player.getStringUUID()));

                        fallingFromDoubleJump = true;
                        jumpState = JumpState.IDLE;
                    }
                    break;
            }
        }
    }
}