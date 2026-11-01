package xela.blockframe.networking.payloads.handlers;

import io.wispforest.owo.network.ServerAccess;
import xela.blockframe.BlockFrame;
import xela.blockframe.data.DataAttachments;
import xela.blockframe.data.typeof.FallDamageDisableStatusAttachment;
import xela.blockframe.networking.ChannelRegistrar;
import xela.blockframe.networking.payloads.records.GenericStringMessagePacket;

public class GenericStringMessagePacketsHandler {
    public static void registerGenericStringMessagePackets(){
        ChannelRegistrar.NET_CHANNEL.registerServerbound(GenericStringMessagePacket.class, GenericStringMessagePacketsHandler::handler);
    }

    private static void handler(GenericStringMessagePacket message, ServerAccess access) {
        switch (message.payload()){
            case "FALL_DAMAGE_DISABLE":
                if (!access.player().hasAttached(DataAttachments.FALLDAMAGE_ATTACHMENT)) {
                    access.player().setAttached(DataAttachments.FALLDAMAGE_ATTACHMENT,
                            new FallDamageDisableStatusAttachment(true));
                }else{
                    access.player().setAttached(DataAttachments.FALLDAMAGE_ATTACHMENT,
                            new FallDamageDisableStatusAttachment(true));
                }
                break;

            case "FALL_DAMAGE_ENABLE":
                if (!access.player().hasAttached(DataAttachments.FALLDAMAGE_ATTACHMENT)) {
                    access.player().setAttached(DataAttachments.FALLDAMAGE_ATTACHMENT,
                            new FallDamageDisableStatusAttachment(false));
                }else{
                    access.player().setAttached(DataAttachments.FALLDAMAGE_ATTACHMENT,
                            new FallDamageDisableStatusAttachment(false));
                }
                break;
        }
    }
}
