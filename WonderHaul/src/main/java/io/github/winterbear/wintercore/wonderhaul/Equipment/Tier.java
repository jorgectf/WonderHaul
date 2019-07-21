package io.github.winterbear.wintercore.wonderhaul.Equipment;

import org.bukkit.Material;

public enum Tier {

    JUNK("Junk","&7", true),
    ORDINARY("Ordinary","&9", true),
    UNUSUAL("Unusual","&a", true),
    RARE("Rare","&e", true),
    ASCENDED("Ascended","&d", true),
    LEGENDARY("Legendary","&5", false),
    MYTHICAL("Mythical","&b", false);

    private String colorCode;

    private String name;

    private boolean enchantable;

    Tier(String name, String colorCode, boolean enchantable){
        this.name = name;
        this.setColorCode(colorCode);
        this.setEnchantable(enchantable);
    }


    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public boolean isEnchantable() {
        return enchantable;
    }

    public void setEnchantable(boolean enchantable) {
        this.enchantable = enchantable;
    }

    public String getTierLore(Material material){
        String materialType = MaterialGroup.fromMaterial(material).getDisplayName();
        return "&6✦ &7Tier&8: &7" + this.getColorCode() + name  + " " + materialType;
    }
}
