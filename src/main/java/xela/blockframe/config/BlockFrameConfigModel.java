package xela.blockframe.config;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.Sync;


@Modmenu(modId = "blockframe")
@Config(name = "Blockframe", wrapperName = "BlockFrameConfigWrapper")
public class BlockFrameConfigModel {
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public boolean should_roll_for_status_effects_on_player_damaged = true;

    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public double chanche_for_status_effects_to_apply = 40;

    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float force_applied_on_movment = 0.2f;
}
