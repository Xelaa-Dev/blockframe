package xela.blockframe.events;

import xela.blockframe.statusEngine.EngineTick;

public class ServerEventRegistrar {
    public static void init(){
        JoinEvent.registerJoinEvent();
        AttackEvent.registerAttackEvent();
        EffectFinishingEvent.registerEffectStartingEvent();
        EngineTick.init();
        DyingEvent.init();
    }
}
