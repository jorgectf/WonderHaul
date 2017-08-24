package io.github.winterbear.wintercore.SetCreativeSlotFilter;

import com.avaje.ebean.LogLevel;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerOptions;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.utility.StreamSerializer;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Created by WinterBear on 08/05/2017.
 */
public class CreativeSlotPacketListener extends PacketAdapter {

    private static final PacketType PACKET_TYPE = PacketType.Play.Client.SET_CREATIVE_SLOT;

    private static final ListenerPriority PRIORITY = ListenerPriority.NORMAL;

    private static final ListenerOptions[] OPTIONS = {ListenerOptions.INTERCEPT_INPUT_BUFFER};

    private PacketReader packetReader;

    private ItemStackFilter itemStackFilter;

    public static CreativeSlotPacketListener build(Plugin plugin){
        PacketAdapter.AdapterParameteters parameters = new PacketAdapter.AdapterParameteters();
        parameters.options(OPTIONS);
        parameters.plugin(plugin);
        parameters.listenerPriority(PRIORITY);
        parameters.types(PACKET_TYPE);
        return new CreativeSlotPacketListener(parameters);

    }

    private CreativeSlotPacketListener(@Nonnull AdapterParameteters params) {
        super(params);
        this.packetReader = new PacketReader();
        this.itemStackFilter = new ItemStackFilter();
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        // Item packets (id: 0x18)
        if (event.getPacketType() ==
                PacketType.Play.Client.SET_CREATIVE_SLOT) {
            try {
                DataInputStream input = event.getNetworkMarker().getInputStream();
                if (input == null) {
                    return;
                }

                try {
                    // Read slot
                    input.readShort();
                    // read  & unfilter itemstack
                    Optional<ItemStack> stack = Optional.ofNullable(packetReader.readItemStack(input, new StreamSerializer()));
                    if(stack.isPresent()){
                        ItemStack filteredStack = itemStackFilter.filterItemStack(stack.get());
                        // And write it back
                        event.getPacket().getItemModifier().write(0, filteredStack);
                    }


                } catch (IOException e) {
                    // Just let ProtocolLib handle it
                    throw new RuntimeException("Cannot undo NBT scrubber.", e);
                } catch (RuntimeException e) {
                    // Do nothing
                }
            } catch (RuntimeException e) {
                Bukkit.getServer().getLogger().log(new LogRecord(Level.INFO, "A Runtime Exception occured while getting the input stream. This can normally be ignored."));
            }
        }
    }
}
