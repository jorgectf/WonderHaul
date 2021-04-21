package io.github.winterbear.wintercore.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collection;
import java.util.UUID;

/**
 * Created by WinterBear on 24/05/2020.
 */
public class MicroblockUtils {

    private static final Base64 BASE_64 = new Base64();

    public static String getTextureUrl(ItemStack head){
        ItemMeta headMeta = head.getItemMeta();
        Class<?> headMetaClass = headMeta.getClass();
        Collection<Property> properties = ReflectionUtils.getField(headMetaClass, "profile", GameProfile.class, 0).get(headMeta).getProperties().get("textures");
        String encodedTexture = properties.stream().filter(p -> p.getName().equals("textures")).findFirst().get().getValue();
        String decoded = new String(BASE_64.decode(encodedTexture));
        String url;
        if(decoded.contains("\"url\":\"")) {
            url = decoded.substring(decoded.indexOf("\"url\":\"") + 7);
        } else {
            url = decoded.substring(decoded.indexOf("url:\"") + 5);
        }
        url = url.substring(0, url.indexOf("\""));
        return url;

    }


    public static ItemStack getCustomSkull(String url) {
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        PropertyMap propertyMap = profile.getProperties();
        if (propertyMap == null) {
            throw new IllegalStateException("Profile doesn't contain a property map");
        }
        byte[] encodedData = BASE_64.encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        propertyMap.put("textures", new Property("textures", new String(encodedData)));
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        ItemMeta headMeta = head.getItemMeta();
        Class<?> headMetaClass = headMeta.getClass();
        ReflectionUtils.getField(headMetaClass, "profile", GameProfile.class, 0).set(headMeta, profile);
        head.setItemMeta(headMeta);
        return head;
    }



}
