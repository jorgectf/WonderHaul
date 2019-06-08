package io.github.winterbear.wintercore.WonderHaul.Tags;

import org.bukkit.entity.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by WinterBear on 05/06/2019.
 */
public class TagRegister {

    private static Map<Player, TagApplication> tags = new ConcurrentHashMap<>();

    public static boolean countdown(Player player){
        if(tags.get(player) == null){
            return false;
        }
        reduce(tags.get(player));
        if(tags.get(player).getCounter() < 1) {
            timeOut(player);
            return false;
        }
        return true;
    }

    public static void register(Player player, TagApplication application){
        tags.put(player, application);
    }

    public static void remove(Player player){
        tags.remove(player);
    }

    public static TagApplication get(Player player){
        return tags.get(player);
    }

    private static void reduce(TagApplication tagApplication){
        tagApplication.setCounter(tagApplication.getCounter() - 1);
    }

    private static void timeOut(Player player){
        tags.get(player).returnTag(player);
        tags.remove(player);
    }

}
