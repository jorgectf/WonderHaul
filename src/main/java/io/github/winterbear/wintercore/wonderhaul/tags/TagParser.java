package io.github.winterbear.wintercore.wonderhaul.tags;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.LoreUtils;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

/**
 * Created by WinterBear on 05/06/2019.
 */
public class TagParser {

    private static final String TAG_KEY = ChatUtils.format(Tags.PREFIX);

    public static Optional<Tag> parse(ItemStack item){
        Optional<String> tagName = LoreUtils.getTag(item, "Tag").stream().findFirst();
        if(tagName.isPresent() && Tags.get(tagName.get()) == null){
            ChatUtils.warn("No Tag by the name of " + tagName + " could be found.");
            return Optional.empty();
        }
        return Optional.of(Tags.get(tagName.get()));
    }


    public static Optional<Tag> parse(String tagName){
        return Optional.ofNullable(Tags.get(tagName));
    }

}
