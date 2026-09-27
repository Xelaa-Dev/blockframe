package xela.blockframe.enums;

import net.minecraft.resources.Identifier;

public enum UiComponentsEnum {
    //These are supposed to identify the UI components for effects, the actual number is gonna be the using the
    // identifier + "_value" id style
    MAIN(Identifier.fromNamespaceAndPath("blockframe", "ui")),
    COLD(Identifier.fromNamespaceAndPath("blockframe", "ui_cold")),
    IMPACT(Identifier.fromNamespaceAndPath("blockframe", "ui_impact")),
    PUNCTURE(Identifier.fromNamespaceAndPath("blockframe", "ui_puncture")),
    SLASH(Identifier.fromNamespaceAndPath("blockframe", "ui_slash"));


    public final Identifier name;

    private UiComponentsEnum(Identifier name) {
        this.name = name;
    }
}