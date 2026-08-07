package xela.blockframe.data;


import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.resources.Identifier;
import xela.blockframe.BlockFrame;
import xela.blockframe.data.typeof.DamageStackAttachment;


public class DataAttachments {
    /*
    Damage stack attachment, should be init onto a player immediately basically and be set to 0
     */
    public static final AttachmentType<DamageStackAttachment> DAMAGE_STACK = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID, "damage_stack"), builder ->
            builder.initializer(
                    () -> new DamageStackAttachment(0,0, null)
            ).syncWith(DamageStackAttachment.STREAM_CODEC, AttachmentSyncPredicate.all())
    );
}
