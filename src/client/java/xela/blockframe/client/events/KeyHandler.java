package xela.blockframe.client.events;

import static xela.blockframe.client.events.doublejump.DoubleJumpRegistrar.registerDoubleJumpKeybind;
import static xela.blockframe.client.events.roll.RollKeyRegistrar.registerRollKeybind;

public class KeyHandler {
    public static void init(){
        registerDoubleJumpKeybind();
        registerRollKeybind();
    }

}
