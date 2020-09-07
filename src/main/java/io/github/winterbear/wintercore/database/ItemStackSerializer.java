package io.github.winterbear.wintercore.database;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by WinterBear on 30/05/2020.
 */
public class ItemStackSerializer {

    public static ItemStack deserialiseItem(String serialisedItem) throws IOException, ClassNotFoundException {
        return deserialiseItems(serialisedItem).get(0);
    }

    public static List<ItemStack> deserialiseItems(String serialisedItems) throws IOException, ClassNotFoundException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(serialisedItems));
        BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

        List<ItemStack> items = new ArrayList<>();

        int size = dataInput.readInt();
        // Read the serialized inventory
        for (int i = 0; i < size; i++) {
            items.add((ItemStack) dataInput.readObject());
        }

        dataInput.close();
        return items;
    }


    public static String serialiseItem(ItemStack item){
        return serialiseItems(Arrays.asList(item));
    }

    public static String serialiseItems(List<ItemStack> items) throws IllegalStateException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);


            dataOutput.writeInt(items.size());

            for (ItemStack item : items) {
                dataOutput.writeObject(item);
            }

            dataOutput.close();
            String result = Base64Coder.encodeLines(outputStream.toByteArray());
            //ChatUtils.info("Serialised with length " + result.length());
            return result;
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }
}
