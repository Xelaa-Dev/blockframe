package xela.blockframe.enums;

public enum BlockframePacketType {
    DOUBLE_JUMP("DOUBLE_JUMP"),
    ROLL("ROLL");


    public final String name;

    private BlockframePacketType(String name){
        this.name = name;
    }

}
