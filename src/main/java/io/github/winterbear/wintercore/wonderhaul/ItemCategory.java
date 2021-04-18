package io.github.winterbear.wintercore.wonderhaul;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.wonderhaul.sockets.SocketType;
import net.md_5.bungee.api.ChatColor;

/**
 * Created by WinterBear on 27/09/2020.
 */
public enum ItemCategory {

    ARTIFACT(ChatColor.of("#ffdd54") + "✦", "Artifact"),
    ORNAMENT(SocketType.ORNAMENT),
    INFUSION(SocketType.INFUSION),
    DECORATION(ChatColor.of("#ffdd54") + "✦", "Decoration"),
    EQUIPMENT(ChatColor.of("#ffdd54") + "✦", "Equipment"),
    TAG(ChatColor.of("#ffdd54") + "✦", "Tag"),
    CRATE(ChatColor.of("#ffdd54") + "✦", "Crate"),
    PACK(ChatColor.of("#6effb1") + "⚒", "Pack");

    private final String symbol;

    private final String displayName;

    ItemCategory(String symbol, String displayName){
        this.symbol = symbol;
        this.displayName = displayName;
    }

    ItemCategory(SocketType socketType){
        this.symbol = socketType.getColor() + socketType.getSymbol();
        this.displayName = socketType.getName();
    }

    public String getSymbol(){
        return symbol;
    }

    public String getFormattedSymbol(){
        return ChatUtils.format(symbol);
    }

    public String getDisplayName(){
        return displayName;
    }

}
