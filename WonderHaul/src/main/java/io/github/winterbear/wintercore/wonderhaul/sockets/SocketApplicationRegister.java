package io.github.winterbear.wintercore.wonderhaul.sockets;

import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by WinterBear on 05/06/2019.
 */
public class SocketApplicationRegister {

    private static Map<UUID, SocketApplication> sockets = new ConcurrentHashMap<>();

    public static boolean countdown(Player player){
        if(sockets.get(player.getUniqueId()) == null){
            return false;
        }
        reduce(sockets.get(player.getUniqueId()));
        if(sockets.get(player.getUniqueId()).getCounter() < 1) {
            timeOut(player);
            return false;
        }
        return true;
    }

    public static void register(Player player, SocketApplication application){
        sockets.put(player.getUniqueId(), application);
    }

    public static void remove(Player player){
        sockets.remove(player.getUniqueId());
    }

    public static SocketApplication get(Player player){
        return sockets.get(player.getUniqueId());
    }

    private static void reduce(SocketApplication tagApplication){
        tagApplication.setCounter(tagApplication.getCounter() - 1);
    }

    private static void timeOut(Player player){
        sockets.get(player.getUniqueId()).returnSocket(player);
        sockets.remove(player.getUniqueId());
    }

}
