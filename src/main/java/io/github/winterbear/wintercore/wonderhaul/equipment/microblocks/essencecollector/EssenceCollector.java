package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.essencecollector;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.ItemUtils;
import io.github.winterbear.wintercore.utils.LoreUtils;
import io.github.winterbear.wintercore.utils.SoundUtils;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.PersistedInventory;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.PersistedItem;
import io.github.winterbear.wintercore.wonderhaul.equipment.Microblock;
import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by WinterBear on 10/08/2020.
 */
public class EssenceCollector extends Microblock {

    private static final Map<Tier, Integer>  UPGRADE_COSTS = getUpgradeCosts();

    private static final Map<Tier, Integer> SACRIFICE_VALUES = getSacrificeValues();

    private static final Map<Tier, Integer> getUpgradeCosts(){

        Map<Tier, Integer> map = new HashMap<Tier, Integer>();

        map.put(Tier.JUNK, 20);
        map.put(Tier.ORDINARY, 60);
        map.put(Tier.UNUSUAL, 200);
        map.put(Tier.RARE, 1000);

        return map;

    }

    private static final Map<Tier, Integer> getSacrificeValues(){

        Map<Tier, Integer> map = new HashMap<Tier, Integer>();

        map.put(Tier.JUNK, 1);
        map.put(Tier.ORDINARY, 10);
        map.put(Tier.UNUSUAL, 30);
        map.put(Tier.RARE, 50);

        return map;
    }

    public EssenceCollector(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public boolean interact(PlayerInteractEvent event, BlockMetadata metadata) {


        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
        Optional<Tier> tier = LoreUtils.getTier(item);
        Optional<ItemStack> upgradeItem = getUpgradeItem(metadata);

        //Is there already an item stored in the collector for upgrading?
        if(upgradeItem.isPresent()){
            //Y - Is the player's tool an item that can be sacrificed?
            if(tier.isPresent() && SACRIFICE_VALUES.containsKey(tier.get())){
                //Y - Calculate essence value and add to internal counter.
                sacrifice(event.getPlayer(), metadata, item, tier.get(), upgradeItem.get());
            } else {
                //N - Message player
                if(item.getType().equals(Material.AIR)){
                    int count = getEssenceCount(metadata);
                    int cost = getUpgradeCost(metadata);
                    ChatUtils.send(event.getPlayer(), "&dEssence Collector &8>> &7Progress - " + count + "/" + cost);
                } else {
                    ChatUtils.send(event.getPlayer(), "&dEssence Collector &8>> &7You can only sacrifice items with a tier.");
                }

            }

        } else {
            //N - Is the player's tool an item that can be upgraded?
            if(tier.isPresent() && UPGRADE_COSTS.containsKey(tier.get())){
                setUpgradeItem(event.getPlayer(), metadata, item, tier.get());
            } else {
                //N - Message player
                if(item.getType().equals(Material.AIR)) {
                    ChatUtils.send(event.getPlayer(), "&dEssence Collector &8>> &7Right click with a tiered item to upgrade it.");
                } else {
                    ChatUtils.send(event.getPlayer(), "&dEssence Collector &8>> &7You cannot upgrade this item.");
                }

            }
        }
        return true;
    }

    private void sacrifice(Player player, BlockMetadata metadata, ItemStack item, Tier tier, ItemStack upgradeItem) {
        int currentEssence = getEssenceCount(metadata);
        int sacrificeValue = SACRIFICE_VALUES.get(tier);
        int totalEssence = currentEssence + sacrificeValue;

        setEssenceCount(metadata, totalEssence);
        player.getInventory().removeItem(ItemUtils.oneOf(item));

        int upgradeCost = getUpgradeCost(metadata);
        if(totalEssence >= upgradeCost){
            //If internal counter meets requirements for item upgrading, upgrade item
            ItemStack upgradedItem = ItemUpgrader.upgrade(upgradeItem, LoreUtils.getTier(upgradeItem).get());
            SoundUtils.playSound(player, Sound.ENTITY_LIGHTNING_BOLT_THUNDER);
            ItemUtils.safelyGiveItem(player, upgradedItem);
            ChatUtils.send(player, "&dEssence Collector &8>> &7Your item was upgraded.");
            clearProperties(metadata);
        } else {
            //Message player
            ChatUtils.send(player, "&dEssence Collector &8>> &7Sacrificed a " +
                    tier.getColor() + tier.getName() + " &7tier item. " + SACRIFICE_VALUES.get(tier)
                    + " essence was added to the collector. " + (upgradeCost - totalEssence) + " essence needed to complete upgrading.");
        }
    }

    private void setUpgradeItem(Player player, BlockMetadata metadata, ItemStack item, Tier tier) {
        //Y - Store item internally and set cost to essence cost
        PersistedInventory inventory = new PersistedInventory("UpgradeItem");
        PersistedItem internalItem = new PersistedItem(ItemUtils.oneOf(item));
        inventory.setItems(Arrays.asList(internalItem));
        metadata.setCustomInventory("UpgradeItem", inventory);
        metadata.setProperty("EssenceCount", "0");
        metadata.setProperty("UpgradeCost", UPGRADE_COSTS.get(tier).toString());
        player.getInventory().removeItem(ItemUtils.oneOf(item));
        ChatUtils.send(player, "&dEssence Collector &8>> &7Placed a " + tier.getColor() + tier.getName() +
                " &7tier item in the collector. Sacrifice items to add essence to the collector. " + UPGRADE_COSTS.get(tier) + " essence needed to complete upgrading.");
    }



    private Optional<ItemStack> getUpgradeItem(BlockMetadata metadata){
        if(metadata.getCustomInventories().containsKey("UpgradeItem")){
            PersistedInventory inventory =  metadata.getCustomInventories().get("UpgradeItem");
            if(!inventory.getItems().isEmpty()){
                return Optional.of(inventory.getItems().get(0).getInternalItem());

            }
        }
        return Optional.empty();
    }

    private int getEssenceCount(BlockMetadata metadata){
        if(metadata.getProperty("EssenceCount").isPresent()){
            return Integer.valueOf(metadata.getProperty("EssenceCount").get());
        }
        return 0;
    }

    private void setEssenceCount(BlockMetadata metadata, int totalEssence){
        metadata.setProperty("EssenceCount", Integer.toString(totalEssence));
    }

    private int getUpgradeCost(BlockMetadata metadata){
        if(metadata.getProperty("UpgradeCost").isPresent()){
            return Integer.valueOf(metadata.getProperty("UpgradeCost").get());
        }
        return 0;
    }

    private void clearProperties(BlockMetadata metadata){
        metadata.getProperties().remove("EssenceCount");
        metadata.getCustomInventories().remove("UpgradeItem");
        metadata.getProperties().remove("UpgradeCost");
    }

    @Override
    public String getReference() {
        return "Essence Collector";
    }
}
