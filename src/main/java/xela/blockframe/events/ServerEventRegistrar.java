package xela.blockframe.events;

public class ServerEventRegistrar {
    public static void init(){
        JoinEvent.registerJoinEvent();
        AttackEvent.registerAttackEvent();
        EffectFinishingEvent.registerEffectFinishingEvent();
        EffectFinishingEvent.registerEffectStartingEvent();
        TickEvent.init();
        DyingEvent.init();
    }
}
