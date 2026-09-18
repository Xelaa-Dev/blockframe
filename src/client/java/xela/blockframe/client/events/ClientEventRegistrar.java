package xela.blockframe.client.events;

import static xela.blockframe.client.events.DoubleJumpRegistrar.registerDoubleJumpKeybind;
import static xela.blockframe.client.events.RollKeyRegistrar.registerRollKeybind;

public class ClientEventRegistrar {
    public static void init() {
        TickEvent.init();
        KeyboardEvent.init();
    }

}
