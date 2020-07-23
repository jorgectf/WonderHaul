package io.github.winterbear.wintercore.wonderhaul.tags;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

/**
 * Created by WinterBear on 05/06/2019.
 */
public class TagParser {

    private static final String TAG_KEY = ChatUtils.format(Tags.PREFIX);

    public static Optional<Tag> parse(ItemStack item){
        Optional<String> loreLine = item.getItemMeta().getLore().stream().filter(line -> line.contains(TAG_KEY)).findFirst();
        if(loreLine.isPresent()){
            String tagName = ChatUtils.uncolored(loreLine.get().substring(TAG_KEY.length()));
            if(Tags.get(tagName) == null){
                ChatUtils.warn("No Tag by the name of " + tagName + " could be found.");
                return Optional.empty();
            }
            return Optional.of(Tags.get(tagName));
        }
        return Optional.empty();
    }


    public static Optional<Tag> parse(String tagName){
        return Optional.ofNullable(Tags.get(tagName));
    }

}
