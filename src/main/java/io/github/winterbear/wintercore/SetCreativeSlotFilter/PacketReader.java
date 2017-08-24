package io.github.winterbear.wintercore.SetCreativeSlotFilter;

import com.comphenix.protocol.utility.MinecraftReflection;
import com.comphenix.protocol.utility.StreamSerializer;
import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;
import org.bukkit.inventory.ItemStack;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by WinterBear on 08/05/2017.
 */
public class PacketReader {

    public ItemStack readItemStack(DataInputStream input, StreamSerializer serializer) throws IOException {
        ItemStack result = null;
        short type = input.readShort();

        if (type >= 0) {
            byte amount = input.readByte();
            short damage = input.readShort();

            result = new ItemStack(type, amount, damage);
            NbtCompound tag = serializer.deserializeCompound(input);

            if (tag != null) {
                result = MinecraftReflection.getBukkitItemStack(result);
                NbtFactory.setItemTag(result, tag);
            }
        }
        return result;
    }
}
