package io.github.winterbear.wintercore.wonderhaul.sockets.application;

import io.github.winterbear.wintercore.utils.LoreUtils;
import io.github.winterbear.wintercore.wonderhaul.sockets.ISocketable;
import io.github.winterbear.wintercore.wonderhaul.sockets.SocketType;
import io.github.winterbear.wintercore.wonderhaul.sockets.infusions.Infusions;
import io.github.winterbear.wintercore.wonderhaul.sockets.ornaments.Ornaments;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by WinterBear on 31/08/2020.
 */
public class SocketParser {

    public static Optional<ISocketable> parse(ItemStack item){
        return Arrays.stream(SocketType.values())
                .flatMap(s -> LoreUtils.getTag(item, s.getName()).stream())
                .map(SocketParser::parse)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    public static Optional<ISocketable> parse(String string){
        Collection<ISocketable> sockets = new ArrayList<>();
        sockets.addAll(Infusions.INFUSIONS);
        sockets.addAll(Ornaments.ORNAMENTS);
        return sockets.stream()
                .filter(i -> i.getAbilityName().equals(string))
                .findFirst();

    }


}
