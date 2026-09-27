package xela.blockframe.client.events;

import static xela.blockframe.client.events.RollKeyRegistrar.registerRollKeybind;

public class KeyboardEvent {
    public static void init(){
        KeyboardRegisterDoubleJumpKeybind();
        KeyboardRegisterRollKeybind();
    }

    private static void KeyboardRegisterRollKeybind() {
       RollKeyRegistrar.registerRollKeybind();
    }
    private static void KeyboardRegisterDoubleJumpKeybind() {
        DoubleJumpRegistrar.registerDoubleJumpKeybind();
    }
}
