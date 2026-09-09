package xela.blockframe.enums;

public enum BlockframePacketType {
    DOUBLE_JUMP("DOUBLE_JUMP"),
    ROLL("ROLL"),
    FALL_DAMAGE_DISABLE("FALL_DAMAGE_DISABLE"),
    FALL_DAMAGE_ENABLE("FALL_DAMAGE_ENABLE");


    public final String name;

    private BlockframePacketType(String name){
        this.name = name;
    }

}
