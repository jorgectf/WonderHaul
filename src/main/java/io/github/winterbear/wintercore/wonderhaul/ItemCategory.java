package io.github.winterbear.wintercore.wonderhaul;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.wonderhaul.sockets.SocketType;

/**
 * Created by WinterBear on 27/09/2020.
 */
public enum ItemCategory {

    ARTIFACT("&6✦", "Artifact"),
    ORNAMENT(SocketType.ORNAMENT),
    INFUSION(SocketType.INFUSION),
    DECORATION("&6✦", "Decoration"),
    EQUIPMENT("&6✦", "Equipment"),
    CRATE("&6✦", "Crate");

    private String symbol;

    private String displayName;

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
