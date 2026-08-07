package xela.blockframe.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import xela.blockframe.BlockFrame;


public class DamageSources {

    public static final ResourceKey<DamageType> SLASH_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID, "slash"));

    public static final ResourceKey<DamageType> PUNCTURE_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID, "puncture"));

    /*
    Technically unneeded, a damage source is a way for the game to tell to the client where damage originated(?) and
    since the cold effect isn't strictly a damage but just a slow down it's not needed

    public static final ResourceKey<DamageType> COLD_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID, "cold"));
     */

    public static void init(){
        //init all static variables in file
    }

}
