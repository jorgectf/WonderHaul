package io.github.winterbear.wintercore.wonderhaul.blockstorage;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.WinterCoreUtils.CommandRegistry;
import io.github.winterbear.WinterCoreUtils.CommandWrapper;
import io.github.winterbear.wintercore.Annotations.Command;

import java.util.Optional;

public class BlockStorageCommands {

    private static Optional<BlockStorage> storage = Optional.empty();

    public static void setStorage(BlockStorage storage){
        BlockStorageCommands.storage = Optional.of(storage);
    }

    @Command(permission = "dev.save")
    public static CommandWrapper saveBlocks(){
        return CommandRegistry.createPlayerCommand("saveBlocks", (player, command, alias, args) -> {

            if(!storage.isPresent()){
                ChatUtils.error(player, "Block Storage not loaded.");
            } else {
                storage.get().saveToDB();
                ChatUtils.send(player, "&9Notice&8: &7Saved block data to db");
            }


        });
    }
}
