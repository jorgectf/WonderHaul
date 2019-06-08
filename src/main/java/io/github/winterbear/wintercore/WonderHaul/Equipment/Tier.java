package io.github.winterbear.wintercore.WonderHaul.Equipment;

public enum Tier {

    JUNK("&7"),
    ORDINARY("&9"),
    UNUSUAL("&a"),
    RARE("&e"),
    ASCENDED("&d"),
    LEGENDARY("&5"),
    MYTHICAL("&b");

    private String colorCode;

    Tier(String colorCode){
        this.colorCode = colorCode;
    }

}
