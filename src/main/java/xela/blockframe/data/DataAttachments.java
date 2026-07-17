package xela.blockframe.data;


import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.resources.Identifier;
import xela.blockframe.BlockFrame;
import xela.blockframe.data.typeof.DamageStackAttachment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DataAttachments {

    public static final AttachmentType<DamageStackAttachment> COLD_DAMAGE_STACK = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID, "cold_damage_stack"), builder ->
            builder.initializer(
                    () -> new DamageStackAttachment(0,0, null)
            ).syncWith(DamageStackAttachment.STREAM_CODEC, AttachmentSyncPredicate.all())
    );

    public static final AttachmentType<DamageStackAttachment> IMPACT_DAMAGE_STACK = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID, "impact_damage_stack"), builder ->
                    builder.initializer(
                            () -> new DamageStackAttachment(0,0, null)
                    ).syncWith(DamageStackAttachment.STREAM_CODEC, AttachmentSyncPredicate.all())
    );

    public static final AttachmentType<DamageStackAttachment> SLASH_DAMAGE_STACK = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID, "slash_damage_stack"), builder ->
                    builder.initializer(
                            () -> new DamageStackAttachment(0,0, null)
                    ).syncWith(DamageStackAttachment.STREAM_CODEC, AttachmentSyncPredicate.all())
    );

    public static final AttachmentType<DamageStackAttachment> PUNCTURE_DAMAGE_STACK = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID, "puncture_damage_stack"), builder ->
                    builder.initializer(
                            () -> new DamageStackAttachment(0,0, null)
                    ).syncWith(DamageStackAttachment.STREAM_CODEC, AttachmentSyncPredicate.all())
    );

    public static List<AttachmentType<DamageStackAttachment>> STACKS = new ArrayList<>(Arrays.asList(
            COLD_DAMAGE_STACK,
            IMPACT_DAMAGE_STACK, 
            SLASH_DAMAGE_STACK,
            PUNCTURE_DAMAGE_STACK));
}
