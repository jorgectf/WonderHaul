package io.github.winterbear.wintercore.wonderhaul.tags;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.WinterCoreUtils.CommandRegistry;
import io.github.winterbear.WinterCoreUtils.CommandWrapper;
import io.github.winterbear.wintercore.Annotations.Command;
import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.utils.ItemUtils;
import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generator;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class TagGenerator implements Generator {

    @Command(permission = "wonderhaul.taggenerator.gettag")
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
        return tag.modify(ItemBuilder.createItem(ChatUtils.format(tag.getDisplayName()), createLore(tag), Material.NAME_TAG));
    }

    private static String createLore(Tag tag){
        return Tags.PREFIX + ChatUtils.uncolored(tag.getDisplayName());
    }

    @Override
    public ItemStack create() {

        if(Chance.roll(90)){ //90%
            return generate(Tags.LORE);
        } else if(Chance.roll(60)) { //6%
            return generate(Tags.REPAIR);
        } else if (Chance.roll(75)){ //3%
            return generate(Tags.BOOST);
        } else { //1%
            return generate(Tags.MIMIC);
        }
    }

    @Override
    public String getName() {
        return "Tag Generator";
    }
}
