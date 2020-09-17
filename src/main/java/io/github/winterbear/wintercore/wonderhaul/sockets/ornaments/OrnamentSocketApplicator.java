package io.github.winterbear.wintercore.wonderhaul.sockets.ornaments;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.LoreUtils;
import io.github.winterbear.wintercore.utils.SoundUtils;
import io.github.winterbear.wintercore.wonderhaul.sockets.application.SocketApplication;
import io.github.winterbear.wintercore.wonderhaul.sockets.application.SocketApplicator;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by WinterBear on 13/09/2020.
 */
public class OrnamentSocketApplicator implements SocketApplicator {
    @Override
    public boolean apply(SocketApplication application, ItemStack item) {
        if(!LoreUtils.getTag(item, "Ornament").isEmpty()){
            List<String> lore = item.getItemMeta().getLore();
            Integer line = LoreUtils.getTag(item, "Ornament").stream()
                    .filter(tag -> lore.stream().anyMatch(l -> l.contains(tag)))
                    .map(tag -> getLine(tag, lore))
                    .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
                    .findFirst().orElse(-1);
            if(line >= 0){
                LoreUtils.setLoreLine(item, line, Ornaments.getOrnamentLore(application));
                ChatUtils.send(application.getPlayer(), "&7The " + application.getSocketable().getColor() + application.getSocketable().getItemName()
                        + " &7Socket was applied!");
                SoundUtils.playSound(application.getPlayer(), application.getSocketable().getSound());
                return true;
            }

        }
        return false;
    }

    private Optional<Integer> getLine(String tag, List<String> lore){
        return lore.stream()
                .filter(l -> l.contains(tag))
                .map(lore::indexOf)
                .findFirst();
    }
}
