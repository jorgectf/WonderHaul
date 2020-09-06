package io.github.winterbear.wintercore.wonderhaul.sockets;

import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import io.github.winterbear.wintercore.wonderhaul.sockets.application.SocketApplication;
import io.github.winterbear.wintercore.wonderhaul.sockets.application.SocketApplicator;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

/**
 * Created by WinterBear on 15/08/2020.
 */
public enum SocketType {

    INFUSION("✵",
            "Infusion",
            Tier.ASCENDED.getColor(),
            "Right click with a weapon or tool that has an Infusion socket! Any existing sockets will be overwritten.",
            new InfusionSocketApplicator()),
    ORNAMENT("❖", "Ornament", Tier.ASCENDED.getColor(), "Right click with a piece of armor that has an Ornament socket! Any existing sockets will be overwritten.", null),
    LEGENDARY_INFUSION("✵", "Legendary Infusion", Tier.LEGENDARY.getColor(), "", null),
    LEGENDARY_ORNAMENT("❖", "Legendary Ornament", Tier.LEGENDARY.getColor(), "", null);


    private String symbol;
    private String name;
    private ChatColor color;
    private String applicationInstructions;
    private SocketApplicator applicator;
    private Function<ISocketable, ItemStack> itemBuilder;

    SocketType(String symbol, String name, ChatColor color, String applicationInstructions, SocketApplicator applicator) {
        this.symbol = symbol;
        this.name = name;
        this.color = color;
        this.applicationInstructions = applicationInstructions;
        this.applicator = applicator;
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

    public String getApplicationInstructions() {
        return applicationInstructions;
    }

    public boolean apply(SocketApplication application, ItemStack item){
        if(!application.getSocketable().getApplicableItems().contains(item.getType())){
            return false;
        }
        return applicator.apply(application, item);

    }
}
