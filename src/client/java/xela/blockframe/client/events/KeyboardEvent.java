package xela.blockframe.client.events;

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
