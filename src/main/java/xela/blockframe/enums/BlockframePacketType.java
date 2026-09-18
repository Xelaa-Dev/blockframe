package xela.blockframe.enums;

public enum BlockframePacketType {
    DOUBLE_JUMP("DOUBLE_JUMP"),
    ROLL("ROLL"),
    HUD_INCREMENT_STACK("HUD_INCREMENT_STACK"),
    HUD_DECREASE_STACK("HUD_DECREASE_STACK");


    public final String name;

    private BlockframePacketType(String name) {
        this.name = name;
    }

}
