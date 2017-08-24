package io.github.winterbear.wintercore;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.*;
import com.comphenix.protocol.utility.MinecraftReflection;
import com.comphenix.protocol.utility.StreamSerializer;
import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;

import io.github.winterbear.wintercore.SetCreativeSlotFilter.CreativeSlotPacketListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by WinterBear on 07/05/2017.
 */
public class WinterCore extends JavaPlugin {

    private ProtocolManager protocolManager;

    private List<? extends PacketAdapter> packetListeners = Arrays.asList(CreativeSlotPacketListener.build(this));

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("ProtocolLib") != null) {
            protocolManager = ProtocolLibrary.getProtocolManager();
            loadPacketListeners();
        }
    }


    private void loadPacketListeners() {
        for(PacketAdapter listener : packetListeners){
            protocolManager.addPacketListener(listener);
        }
    }
}
