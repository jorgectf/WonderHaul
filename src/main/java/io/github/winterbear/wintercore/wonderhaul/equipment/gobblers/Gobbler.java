package io.github.winterbear.wintercore.wonderhaul.equipment.gobblers;

import io.github.winterbear.wintercore.utils.*;
import io.github.winterbear.wintercore.wonderhaul.ItemCategory;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by WinterBear on 26/09/2020.
 */
public class Gobbler implements Listener {

    private final Map<Player, Long> clickCooldown = new ConcurrentHashMap<>();

    private String displayName;

    private List<Material> items;

    private ChatColor color;

    private List<GobblerReward> rewards;

    private TexturedHead openTexture;

    private TexturedHead closedTexture;

    private String wasteAction;

    public Gobbler(JavaPlugin plugin, String displayName, List<Material> items, ChatColor color, List<GobblerReward> rewards, TexturedHead openTexture, TexturedHead closedTexture, String wasteAction) {
        this.displayName = displayName;
        this.items = items;
        this.color = color;
        this.rewards = rewards;
        this.openTexture = openTexture;
        this.closedTexture = closedTexture;
        this.wasteAction = wasteAction;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityPickupItemEvent(EntityPickupItemEvent event){
        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            if(Arrays.stream(player.getInventory().getContents())
                    .filter(this::itemIsGobbler)
                .anyMatch(item -> LoreUtils.getTag(item, ItemCategory.EQUIPMENT.getDisplayName()).contains("Gobbler"))){
                this.onPickup(event);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(event.isRightClick()){
            ItemStack item = event.getCurrentItem();
            if(itemIsGobbler(item)) {
                List<String> tags = LoreUtils.getTag(item, ItemCategory.EQUIPMENT.getDisplayName());
                if (tags.contains("Inactive Gobbler") || tags.contains("Gobbler")) {
                    event.getClickedInventory().setItem(event.getSlot(), toggle(item));
                    event.setCancelled(true);
                }
            }
        }


    }

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event) {
        //ChatUtils.info("Detected possible gobbler event");
        if(InteractEventUtils.eventIsRightClickEvent(event, clickCooldown)){
            //ChatUtils.info("Detected right click event");
            ItemStack itemInMainHand = event.getPlayer().getInventory().getItemInMainHand();
            if(itemIsGobbler(itemInMainHand)) {
                //ChatUtils.info("Item is gobbler");
                List<String> tags = LoreUtils.getTag(itemInMainHand, ItemCategory.EQUIPMENT.getDisplayName());
                if (tags.contains("Inactive Gobbler") || tags.contains("Gobbler")) {
                    //ChatUtils.info("Toggling...");
                    event.getPlayer().getInventory().setItemInMainHand(toggle(itemInMainHand));
                    event.setCancelled(true);
                }
            }
        }
    }

    private boolean itemIsGobbler(ItemStack itemStack){
        List<String> tags = LoreUtils.getTag(itemStack, ItemCategory.EQUIPMENT.getDisplayName());
        return itemStack != null
                && itemStack.getType() == Material.PLAYER_HEAD
                && (tags.contains("Inactive Gobbler") || tags.contains("Gobbler"))
                && (MicroblockUtils.getTextureUrl(itemStack).equals(openTexture.getTextureURL()) ||
                MicroblockUtils.getTextureUrl(itemStack).equals(closedTexture.getTextureURL()));
    }

    public void onPickup(EntityPickupItemEvent event){

        if(event.getEntity() instanceof Player && items.contains(event.getItem().getItemStack().getType())){
            event.setCancelled(true);
            event.getItem().remove();
            for(int i = 0; i < event.getItem().getItemStack().getAmount(); i++) {
                rewards.forEach(reward -> drop((Player) event.getEntity(), reward));
            }
        }
    }

    private void drop(Player player, GobblerReward reward){
        if(reward.getChance().roll()){
            ItemUtils.safelyGiveItem(player, reward.getReward());
        }
    }

    public ItemStack toggle(ItemStack item){
        if(MicroblockUtils.getTextureUrl(item).equals(openTexture.getTextureURL())){
            return GobblerGenerator.getInactiveGobbler(this);
        } else {
            return GobblerGenerator.getActiveGobbler(this);
        }
    }

    public String getDisplayName() {
        return "☀ " + displayName;
    }

    public List<Material> getItems() {
        return items;
    }

    public List<GobblerReward> getRewards() {
        return rewards;
    }

    public TexturedHead getOpenTexture() {
        return openTexture;
    }

    public TexturedHead getClosedTexture() {
        return closedTexture;
    }

    public String getWasteAction() {
        return wasteAction;
    }

    public ChatColor getColor() {
        return color;
    }
}
