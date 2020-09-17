package io.github.winterbear.wintercore.wonderhaul.sockets;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.WinterCoreUtils.CommandRegistry;
import io.github.winterbear.WinterCoreUtils.CommandWrapper;
import io.github.winterbear.wintercore.Annotations.Command;
import io.github.winterbear.wintercore.utils.ItemUtils;
import io.github.winterbear.wintercore.utils.RandomUtils;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generator;
import io.github.winterbear.wintercore.wonderhaul.sockets.infusions.Infusions;
import io.github.winterbear.wintercore.wonderhaul.sockets.ornaments.Ornaments;
import org.apache.commons.lang.StringUtils;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

/**
 * Created by WinterBear on 06/09/2020.
 */
public class SocketGenerator implements Generator {


    @Override
    public ItemStack create() {
        ISocketable infusion = RandomUtils.getRandomElementOf(Infusions.INFUSIONS);
        return infusion.getItem();
    }

    @Override
    public String getName() {
        return "Socket Generator";
    }

    @Command(permission = "wonderhaul.socketgenerator.getsocket")
    public static CommandWrapper getSocket(){
        return CommandRegistry.createPlayerCommand("getsocket", (player, command, label, string) -> {
            if(string.length == 0){
                ChatUtils.error(player, "No socket specified!");
                return;
            }
            String socketName = StringUtils.join(string, ' ');
            Optional<ISocketable> socket = Infusions.INFUSIONS.stream()
                    .filter(i -> i.getItemName().equals(socketName))
                    .findFirst();
            if(!socket.isPresent()){
                socket = Ornaments.ORNAMENTS.stream()
                        .filter(i -> i.getItemName().equals(socketName))
                        .findFirst();
            }
            if(socket.isPresent()){
                ItemUtils.safelyGiveItem(player, socket.get().getItem());
                ChatUtils.send(player, "&7Generated socket " + socket.get().getItemName());
            } else {
                ChatUtils.error(player, "No socket by the name " + socketName);
            }

        });
    }


}
