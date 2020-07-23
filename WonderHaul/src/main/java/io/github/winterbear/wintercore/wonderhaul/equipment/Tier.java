package io.github.winterbear.wintercore.wonderhaul.equipment;

import org.bukkit.Material;

public enum Tier {

    JUNK("Junk","&7", true),
    ORDINARY("Ordinary","&9", true),
    UNUSUAL("Unusual","&a", true),
    RARE("Rare","&6", true),
    ASCENDED("Ascended","&d", true),
    LEGENDARY("Legendary","&5", false),
    MYSTERIOUS("Mysterious", "&d", false),
    MYTHICAL("Mythical","&b", false);

    private String colorCode;

    private String name;

    private boolean enchantable;

    Tier(String name, String colorCode, boolean enchantable){
        this.name = name;
        this.setColorCode(colorCode);
        this.setEnchantable(enchantable);
    }

    public String getName(){ return this.name; }

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
        return getTierLore("Equipment", materialType);
    }

    public String getTierLore(String prefix, String materialType){
        return "&6✦ &7" + prefix + "&8: &7" + this.getColorCode() + name  + " " + materialType;
    }

    public String getColouredLore(String prefix, String materialType){
        return "&6✦ &7" + prefix + "&8: &7" + this.getColorCode() + materialType;
    }

    public String getColouredLore(String icon, String prefix, String materialType){
        return "&6" + icon + " &7" + prefix + "&8: &7" + this.getColorCode() + materialType;
    }
}
