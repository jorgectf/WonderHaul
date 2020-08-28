package io.github.winterbear.wintercore.wonderhaul.equipment;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;

public enum Tier {

    JUNK("Junk", ChatColor.of("#f9ffb8"), true, true),
    ORDINARY("Ordinary",ChatColor.of("#42cbf5"), true, true),
    UNUSUAL("Unusual",ChatColor.of("#42f5ad"), true, true),
    RARE("Rare",ChatColor.of("#ffd754"), true, true),
    ASCENDED("Ascended",ChatColor.of("#eb236a"), true, false),
    LEGENDARY("Legendary",ChatColor.of("#8000ff"), false, false),
    MYSTERIOUS("Mysterious", ChatColor.of("#b186db"), false, false),
    MYTHICAL("Mythical",ChatColor.of("#b9eaf0"), false, false);

    private ChatColor color;

    private String name;

    private boolean enchantable;

    private boolean upgradable;

    Tier(String name, ChatColor color, boolean enchantable, boolean upgradable){
        this.name = name;
        this.setColor(color);
        this.setEnchantable(enchantable);
        this.upgradable = upgradable;
    }

    public static Tier upgrade(Tier tier){
        switch(tier){
            case JUNK:
                return ORDINARY;
            case ORDINARY:
                return UNUSUAL;
            case UNUSUAL:
                return RARE;
            case RARE:
                return ASCENDED;
            case ASCENDED:
                return LEGENDARY;
            case LEGENDARY:
                return MYTHICAL;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public String getName(){ return this.name; }

    public ChatColor getColor() {
        return color;
    }

    public void setColor(ChatColor colorCode) {
        this.color = colorCode;
    }

    public boolean isEnchantable() {
        return enchantable;
    }

    public boolean isUpgradable(){
        return upgradable;
    }

    public void setEnchantable(boolean enchantable) {
        this.enchantable = enchantable;
    }

    public String getTierLore(Material material){
        String materialType = MaterialGroup.fromMaterial(material).getDisplayName();
        return getTierLore("Equipment", materialType);
    }

    public String getTierLore(String prefix, String materialType){
        return "&6✦ &7" + prefix + "&8: &7" + this.getColor() + name  + " " + materialType;
    }

    public String getColouredLore(String prefix, String materialType){
        return "&6✦ &7" + prefix + "&8: &7" + this.getColor() + materialType;
    }

    public String getColouredLore(String icon, String prefix, String materialType){
        return "&6" + icon + " &7" + prefix + "&8: &7" + this.getColor() + materialType;
    }
}
