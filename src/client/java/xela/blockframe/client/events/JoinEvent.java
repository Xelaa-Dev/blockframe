package xela.blockframe.client.events;

import io.wispforest.owo.ui.hud.Hud;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import xela.blockframe.client.HUD.DrawStatusEffectOnHUD;
import xela.blockframe.enums.UiComponentsEnum;

public class JoinEvent {
    public static void init(){
        ClientPlayConnectionEvents.JOIN.register(JoinEvent::getJoin);
    }

    private static void getJoin(ClientPacketListener listener, PacketSender sender, Minecraft client) {

        var component = Hud.getComponent(UiComponentsEnum.MAIN.name);
        /*
        if (component == null) {
            component.remove();
            DrawStatusEffectOnHUD.init();
        }
        */
    }
}
