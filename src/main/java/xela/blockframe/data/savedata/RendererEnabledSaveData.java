package xela.blockframe.data.savedata;

import com.mojang.serialization.Codec;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;
import xela.blockframe.BlockFrame;

public class RendererEnabledSaveData extends SavedData {
    private boolean isRendererEnabled = false;

    public RendererEnabledSaveData() {
    }

    public RendererEnabledSaveData(boolean isRendererEnabled) {
        this.isRendererEnabled = isRendererEnabled;
    }

    public static RendererEnabledSaveData getOverworldDataStorage(MinecraftServer server) {

        ServerLevel level = server.getLevel(ServerLevel.OVERWORLD);

        if (level == null) {
            return new RendererEnabledSaveData();
        }

        return level.getDataStorage().computeIfAbsent(TYPE);
    }

    public static RendererEnabledSaveData getNetherDataStorage(MinecraftServer server) {

        ServerLevel level = server.getLevel(ServerLevel.NETHER);

        if (level == null) {
            return new RendererEnabledSaveData();
        }

        return level.getDataStorage().computeIfAbsent(TYPE);
    }

    public static RendererEnabledSaveData getEndDataStorage(MinecraftServer server) {

        ServerLevel level = server.getLevel(ServerLevel.END);

        if (level == null) {
            return new RendererEnabledSaveData();
        }

        return level.getDataStorage().computeIfAbsent(TYPE);
    }

    public void setRendererEnabled(boolean rendererEnabled) {
        isRendererEnabled = rendererEnabled;
        setDirty();
    }

    public Boolean isRendererEnabled() {
        return isRendererEnabled;
    }

    public static final Codec<RendererEnabledSaveData> CODEC = Codec.BOOL.xmap(
            RendererEnabledSaveData::new,
            RendererEnabledSaveData::isRendererEnabled
    );


    public static final SavedDataType<RendererEnabledSaveData> TYPE = new SavedDataType<>(
            Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID, "renderer_enabled_save_data"),
            RendererEnabledSaveData::new,
            CODEC,
            null
    );
}


