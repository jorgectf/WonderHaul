package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.essencecollector;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.*;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.PersistedInventory;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.PersistedItem;
import io.github.winterbear.wintercore.wonderhaul.equipment.MaterialGroup;
import io.github.winterbear.wintercore.wonderhaul.equipment.Microblock;
import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import io.github.winterbear.wintercore.wonderhaul.equipment.enchanting.EnchantConfig;
import io.github.winterbear.wintercore.wonderhaul.equipment.enchanting.EnchantTierConfig;
import io.github.winterbear.wintercore.wonderhaul.equipment.enchanting.Enchantments;
import io.github.winterbear.wintercore.wonderhaul.sockets.SocketType;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.stream.Collectors;

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

        //Is there already an item stored in the collector for upgrading?
        if(metadata.getCustomInventories().containsKey("UpgradeItem")){
            //Y - Is the player's tool an item that can be sacrificed?
            if(tier.isPresent() && SACRIFICE_VALUES.containsKey(tier.get())){
                //Y - Calculate essence value and add to internal counter.
                int count = Integer.parseInt(metadata.getProperty("EssenceCount").get());
                count = count + SACRIFICE_VALUES.get(tier.get());
                metadata.setProperty("EssenceCount", Integer.toString(count));
                int cost = Integer.parseInt(metadata.getProperty("UpgradeCost").get());
                event.getPlayer().getInventory().removeItem(ItemUtils.oneOf(item));
                if(count >= cost){
                    //If internal counter meets requirements for item upgrading, upgrade item
                    ItemStack internalItem = metadata.getCustomInventories().get("UpgradeItem").getItems().get(0).getInternalItem();
                    ItemStack newItem = upgrade(internalItem, LoreUtils.getTier(internalItem).get());
                    SoundUtils.playSound(event.getPlayer(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER);
                    ItemUtils.safelyGiveItem(event.getPlayer(), newItem);
                    ChatUtils.send(event.getPlayer(), "&dEssence Collector &8>> &7Your item was upgraded.");
                    metadata.getProperties().remove("EssenceCount");
                    metadata.getCustomInventories().remove("UpgradeItem");
                    metadata.getProperties().remove("UpgradeCost");
                } else {
                    //Message player
                    ChatUtils.send(event.getPlayer(), "&dEssence Collector &8>> &7Sacrificed a " +
                            tier.get().getColor() + tier.get().getName() + " &7tier item. " + SACRIFICE_VALUES.get(tier.get())
                    + " essence was added to the collector. " + (cost - count) + " essence needed to complete upgrading.");
                }
            } else {
                //N - Message player
                if(item.getType().equals(Material.AIR)){
                    int count = Integer.parseInt(metadata.getProperty("EssenceCount").get());
                    int cost = Integer.parseInt(metadata.getProperty("UpgradeCost").get());
                    ChatUtils.send(event.getPlayer(), "&dEssence Collector &8>> &7Progress - " + count + "/" + cost);
                } else {
                    ChatUtils.send(event.getPlayer(), "&dEssence Collector &8>> &7You can only sacrifice items with a tier.");
                }

            }

        } else {
            //N - Is the player's tool an item that can be upgraded?
            if(tier.isPresent() && UPGRADE_COSTS.containsKey(tier.get())){
                //Y - Store item internally and set cost to essence cost
                PersistedInventory inventory = new PersistedInventory("UpgradeItem");
                PersistedItem internalItem = new PersistedItem(ItemUtils.oneOf(item));
                inventory.setItems(Arrays.asList(internalItem));
                metadata.setCustomInventory("UpgradeItem", inventory);
                metadata.setProperty("EssenceCount", "0");
                metadata.setProperty("UpgradeCost", UPGRADE_COSTS.get(tier.get()).toString());
                event.getPlayer().getInventory().removeItem(ItemUtils.oneOf(item));
                ChatUtils.send(event.getPlayer(), "&dEssence Collector &8>> &7Placed a " + tier.get().getColor() + tier.get().getName() +
                        " &7tier item in the collector. Sacrifice items to add essence to the collector. " + UPGRADE_COSTS.get(tier.get()) + " essence needed to complete upgrading.");
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

    public ItemStack upgrade(ItemStack item, Tier tier){
        ItemStack newItem = ItemUtils.oneOf(item);
        Tier newTier = Tier.upgrade(tier);
        Optional<EnchantTierConfig> config = Enchantments.lookup(MaterialGroup.fromMaterial(newItem.getType()), newTier);
        if(config.isPresent()){
            int max = config.get().getMaxEnchants();
            int min = config.get().getMinEnchants();
            int numEnchants = RandomUtils.getIntegerBetween(min, max);
            int current = newItem.getEnchantments().keySet().size();
            int newEnchants = numEnchants - current;
            Map<Enchantment, Integer> possibleUpgrades = calculateUpgradeChances(newItem, tier, newTier);
            int maxUpgrades = possibleUpgrades.values().stream()
                    .reduce(0, Integer::sum);
            int upgradechances = Math.min(maxUpgrades, 5);
            if(newEnchants > 0) {
                List<Enchantment> possibleEnchantments = config.get()
                        .getEnchantConfigs().stream()
                        .map(e -> e.getEnchantment())
                        .filter(e -> !newItem.getEnchantments().keySet().contains(e))
                        .collect(Collectors.toList());
                for(int i = 0; i < 3; i++){
                    if(!possibleEnchantments.isEmpty() && i < newEnchants){
                        Enchantment newEnchantment = RandomUtils.getRandomElementOf(possibleEnchantments);
                        Optional<EnchantConfig> econf = config.get().getEnchantConfigs()
                                .stream()
                                .filter(e -> e.getEnchantment().equals(newEnchantment))
                                .findFirst();
                        if(econf.isPresent()){
                            econf.get().generateEnchantment().enchant(newItem);
                            possibleEnchantments.remove(newEnchantment);
                            upgradechances=- 1;
                        }
                    }
                }

            }
            while(upgradechances-- > 0 && !possibleUpgrades.isEmpty()){
                Enchantment enchantment = RandomUtils.getRandomElementOf(possibleUpgrades.keySet());
                EnchantmentUtils.boost(newItem, enchantment, newItem.getItemMeta().getEnchantLevel(enchantment));
                int remainingBoosts = possibleUpgrades.get(enchantment) - 1;
                if(remainingBoosts < 1){
                    possibleUpgrades.remove(enchantment);
                } else {
                    possibleUpgrades.put(enchantment, remainingBoosts);
                }
            }
        }

        String oldName = ChatUtils.uncolored(newItem.getItemMeta().getDisplayName());
        ItemBuilder.setDisplayName(newItem, newTier.getColor() + oldName);
        LoreUtils.changeTier(newItem, newTier);
        if(newTier == Tier.ASCENDED){
            if(MaterialGroup.ARMOR.contains(MaterialGroup.fromMaterial(newItem.getType()))){
                LoreUtils.addEmptySocket(newItem, SocketType.ORNAMENT);
            } else {
                LoreUtils.addEmptySocket(newItem, SocketType.INFUSION);
            }
        }
        return newItem;
    }

    private Map<Enchantment, Integer> calculateUpgradeChances(ItemStack item, Tier tier, Tier newTier){
        Optional<EnchantTierConfig> config = Enchantments.lookup(MaterialGroup.fromMaterial(item.getType()), newTier);
        Map<Enchantment, Integer> currentEnchants = item.getEnchantments();
        Map<Enchantment, Integer> availableUpgrades = new HashMap<>();
        if(config.isPresent()){
            for (Enchantment enchantment : item.getEnchantments().keySet()){
                int currentLevel = currentEnchants.get(enchantment);
                int max = config.get().getEnchantConfigs().stream()
                        .filter(e -> e.getEnchantment().equals(enchantment))
                        .map(e -> e.getMaxLevel())
                        .findFirst().orElse(0);
                if (max > currentLevel){
                    availableUpgrades.put(enchantment, max - currentLevel);
                }
            }
        }

        return availableUpgrades;



    }


    @Override
    public String getReference() {
        return "Essence Collector";
    }
}
