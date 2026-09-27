package xela.blockframe.events;

import net.fabricmc.fabric.impl.entity.event.effect.EffectEventContextImpl;

public class ServerEventRegistrar {
    public static void init() {
        JoinEvent.registerJoinEvent();
        AttackEvent.registerAttackEvent();
        EffectFinishingEvent.registerEffectStartingEvent();
        TickEvent.init();
        DyingEvent.init();
    }
}
