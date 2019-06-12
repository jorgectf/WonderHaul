package io.github.winterbear.wintercore.wonderhaul.Tags;

import io.github.winterbear.wintercore.Annotations.Command;
import io.github.winterbear.wintercore.CommandRegistry;
import io.github.winterbear.wintercore.CommandWrapper;
import io.github.winterbear.wintercore.utils.ChatUtils;
import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.utils.ItemUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class TagGenerator {

    @Command(permission = "taggenerator.gettag")
    public static CommandWrapper getTag(){
        return CommandRegistry.createPlayerCommand("gettag", (player, command, label, string) -> {
           if(string.length == 0){
               ChatUtils.error(player, "No tag specified!");
               return;
           }
           String tagName = StringUtils.join(string, ' ');
           Optional<Tag> tag = TagParser.parse(tagName);
           if(tag.isPresent()){
               ItemUtils.safelyGiveItem(player, generate(tag.get()));
               ChatUtils.send(player, "&7Generated tag " + tag.get().getDisplayName());
           } else {
               ChatUtils.error(player, "No tag by the name " + tagName);
           }

        });
    }

    public static ItemStack generate(Tag tag){
        return ItemBuilder.createItem(ChatUtils.convert(tag.getDisplayName()), createLore(tag), Material.NAME_TAG);
    }

    private static String createLore(Tag tag){
        return "&7Tag&8: &7" + ChatUtils.uncolored(tag.getDisplayName());
    }

}
