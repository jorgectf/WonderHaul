package io.github.winterbear.wintercore.wonderhaul.sockets;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.ItemUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 05/06/2019.
 */
public class SocketApplication {

    private int counter;

    private ItemStack socketItem;

    private Socketable socket;

    private Player player;

    public SocketApplication(int counter, ItemStack tagItem, Socketable socket, Player player) {
        this.counter = counter;
        this.socketItem = tagItem;
        this.socket = socket;
        this.player = player;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public ItemStack getSocketItem() {
        return socketItem;
    }

    public Player getPlayer() { return player; }

    public void setSocketItem(ItemStack socketItem) {
        this.socketItem = socketItem;
    }

    public Socketable getSocketable() {
        return socket;
    }

    public void setSocketable(Socketable socket) {
        this.socket = socket;
    }

    public void returnSocket(Player player){
        ChatUtils.send(player, socket.getItemName() + ": &7Socket application cancelled. Giving you back your item...");
        ItemUtils.safelyGiveItem(player, socketItem);
    }
}
