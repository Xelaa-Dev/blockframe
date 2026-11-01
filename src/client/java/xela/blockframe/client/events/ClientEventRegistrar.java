package xela.blockframe.client.events;

public class ClientEventRegistrar {
    public static void init() {
        TickEvent.init();
        KeyboardEvent.init();
        JoinEvent.init();
    }

}
