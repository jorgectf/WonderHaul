package io.github.winterbear.wintercore.wonderhaul.sockets;

import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import net.md_5.bungee.api.ChatColor;

/**
 * Created by WinterBear on 15/08/2020.
 */
public enum SocketType {

    INFUSION("✵", "Infusion", Tier.ASCENDED.getColor()),
    ORNAMENT("❖", "Ornament", Tier.ASCENDED.getColor()),
    LEGENDARY_INFUSION("✵", "Legendary Infusion", Tier.LEGENDARY.getColor()),
    LEGENDARY_ORNAMENT("❖", "Legendary Ornament", Tier.LEGENDARY.getColor());


    private String symbol;
    private String name;
    private ChatColor color;

    SocketType(String symbol, String name, ChatColor color) {
        this.symbol = symbol;
        this.name = name;
        this.color = color;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public ChatColor getColor() {
        return color;
    }
}
