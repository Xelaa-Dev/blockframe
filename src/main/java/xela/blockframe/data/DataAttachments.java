package xela.blockframe.data;


import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.resources.Identifier;
import xela.blockframe.BlockFrame;
import xela.blockframe.statusEngine.EntityStatusAttachment;
import xela.blockframe.data.typeof.FallDamageDisableStatusAttachment;


public class DataAttachments {
    /*
    Damage stack attachment, should be init onto a player immediately basically and be set to 0
     */
    public static final AttachmentType<EntityStatusAttachment> STATUS_ATTACHMENT = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID, "damage_stacks"),
            builder -> builder
                    .initializer(EntityStatusAttachment::new)
                    .persistent(EntityStatusAttachment.CODEC)
                    .syncWith(EntityStatusAttachment.STREAM_CODEC, AttachmentSyncPredicate.all())
    );

    public static final AttachmentType<FallDamageDisableStatusAttachment> FALLDAMAGE_ATTACHMENT = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID, "fall_damage"),
            builder -> builder
                    .initializer(() -> new FallDamageDisableStatusAttachment(false))
                    .persistent(FallDamageDisableStatusAttachment.CODEC)
                    .syncWith(FallDamageDisableStatusAttachment.STREAM_CODEC, AttachmentSyncPredicate.all())

    );

    public static void register() {
    }
}
