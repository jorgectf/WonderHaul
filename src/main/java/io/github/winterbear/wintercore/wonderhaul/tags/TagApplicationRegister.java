package io.github.winterbear.wintercore.wonderhaul.tags;

import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by WinterBear on 05/06/2019.
 */
public class TagApplicationRegister {

    private static Map<UUID, TagApplication> tags = new ConcurrentHashMap<>();

    public static boolean countdown(Player player){
        if(tags.get(player.getUniqueId()) == null){
            return false;
        }
        reduce(tags.get(player.getUniqueId()));
        if(tags.get(player.getUniqueId()).getCounter() < 1) {
            timeOut(player);
            return false;
        }
        return true;
    }

    public static void register(Player player, TagApplication application){
        tags.put(player.getUniqueId(), application);
    }

    public static void remove(Player player){
        tags.remove(player.getUniqueId());
    }

    public static TagApplication get(Player player){
        return tags.get(player.getUniqueId());
    }

    private static void reduce(TagApplication tagApplication){
        tagApplication.setCounter(tagApplication.getCounter() - 1);
    }

    private static void timeOut(Player player){
        tags.get(player.getUniqueId()).returnTag(player);
        tags.remove(player.getUniqueId());
    }

}
