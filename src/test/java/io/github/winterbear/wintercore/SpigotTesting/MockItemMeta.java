package io.github.winterbear.wintercore.SpigotTesting;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import org.mockito.Mock;
import sun.security.x509.AttributeNameEnumeration;

import java.util.*;

import static org.mockito.Mockito.mock;

/**
 * Created by WinterBear on 30/09/2018.
 */
public class MockItemMeta implements ItemMeta{

    private String displayName;
    private String localisedName;
    private List<String> lore;
    private Map<Enchantment, Integer> enchants;
    private boolean unbreakable = false;
    Set<ItemFlag> itemFlags;

    public MockItemMeta() {
        this.enchants = new HashMap<>();
        this.lore = new ArrayList<>();
        this.itemFlags = new HashSet<>();
    }

    public MockItemMeta(String displayName, String localisedName, List<String> lore, Map<Enchantment, Integer> enchants, boolean unbreakable, Set<ItemFlag> itemFlags){
        this.displayName = displayName;
        this.localisedName = localisedName;
        this.lore = lore;
        this.enchants = enchants;
        this.unbreakable = unbreakable;
        this.itemFlags = itemFlags;
    }

    @Override
    public boolean hasDisplayName() {
        return displayName != null;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(String s) {
        this.displayName = s;
    }

    @Override
    public boolean hasLocalizedName() {
        return localisedName != null;
    }

    @Override
    public String getLocalizedName() {
        return this.localisedName;
    }

    @Override
    public void setLocalizedName(String s) {
        this.localisedName = s;
    }

    @Override
    public boolean hasLore() {
        return lore != null;
    }

    @Override
    public List<String> getLore() {
        return lore;
    }

    @Override
    public void setLore(List<String> list) {
        this.lore = list;
    }

    @Override
    public boolean hasEnchants() {
        return enchants != null;
    }

    @Override
    public boolean hasEnchant(Enchantment enchantment) {
        return enchants.containsKey(enchantment);
    }

    @Override
    public int getEnchantLevel(Enchantment enchantment) {
        return enchants.get(enchantment);
    }

    @Override
    public Map<Enchantment, Integer> getEnchants() {
        return enchants;
    }

    @Override
    public boolean addEnchant(Enchantment enchantment, int i, boolean b) {
        return enchants.put(enchantment, i) == null;
    }

    @Override
    public boolean removeEnchant(Enchantment enchantment) {
        return enchants.remove(enchantment, enchants.get(enchantment));
    }

    @Override
    public boolean hasConflictingEnchant(Enchantment enchantment) {
        return enchants.containsKey(enchantment);
    }

    @Override
    public void addItemFlags(ItemFlag... itemFlags) {
        this.itemFlags.addAll(Arrays.asList(itemFlags));
    }

    @Override
    public void removeItemFlags(ItemFlag... itemFlags) {
        this.itemFlags.removeAll(Arrays.asList(itemFlags));
    }

    @Override
    public Set<ItemFlag> getItemFlags() {
        return itemFlags;
    }

    @Override
    public boolean hasItemFlag(ItemFlag itemFlag) {
        return itemFlags.contains(itemFlag);
    }

    @Override
    public boolean isUnbreakable() {
        return unbreakable;
    }

    @Override
    public void setUnbreakable(boolean b) {
        this.unbreakable = b;
    }

    @Override
    public ItemMeta clone() {
        return new MockItemMeta(this.displayName, this.localisedName, this.lore, this.enchants, this.unbreakable, this.itemFlags);
    }

    @Override
    public Spigot spigot() {
        return mock(Spigot.class);
    }

    @Override
    public Map<String, Object> serialize() {
        return null;
    }
}
