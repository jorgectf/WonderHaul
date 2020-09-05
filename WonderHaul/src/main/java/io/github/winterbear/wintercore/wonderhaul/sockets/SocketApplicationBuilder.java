package io.github.winterbear.wintercore.wonderhaul.sockets;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SocketApplicationBuilder {
    private int counter;
    private ItemStack socketItem;
    private Socketable socket;
    private Player player;

    public static SocketApplicationBuilder create(){
        return new SocketApplicationBuilder();
    }

    public SocketApplicationBuilder forPlayer(Player player){
        this.player = player;
        return this;
    }

    public SocketApplicationBuilder withCounter(int counter) {
        this.counter = counter;
        return this;
    }

    public SocketApplicationBuilder withSocketItem(ItemStack socketItem) {
        this.socketItem = socketItem;
        return this;
    }

    public SocketApplicationBuilder withSocket(Socketable socket) {
        this.socket = socket;
        return this;
    }

    public SocketApplication build() {
        return new SocketApplication(counter, socketItem, socket, player);
    }
}