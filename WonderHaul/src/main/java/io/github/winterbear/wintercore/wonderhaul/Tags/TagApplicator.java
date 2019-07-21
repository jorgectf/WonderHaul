package io.github.winterbear.wintercore.wonderhaul.Tags;

import io.github.winterbear.wintercore.utils.ChatUtils;
import io.github.winterbear.wintercore.utils.ItemUtils;
import io.github.winterbear.wintercore.utils.RepeatingTaskUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by WinterBear on 05/06/2019.
 */
public class TagApplicator implements Listener {

    private static final int BASE_COUNTER = 15;

    private final JavaPlugin plugin;

    private static final Map<Player, Long> clickCooldown = new HashMap<>();

    public TagApplicator(JavaPlugin plugin){
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private boolean eventIsRightClickEvent(PlayerInteractEvent event){
        if(clickCooldown.containsKey(event.getPlayer()) && System.currentTimeMillis() - clickCooldown.get(event.getPlayer()) < 1000L){
            //duplicate event
            return false;
        }
        clickCooldown.put(event.getPlayer(), System.currentTimeMillis());
        return event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK);
    }


    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event) {
        if(eventIsRightClickEvent(event)){
            if(begin(event.getPlayer())){
                event.setCancelled(true);
            }
        }
    }

    private boolean begin(Player player){
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        if(itemInMainHand != null && itemInMainHand.getType().equals(Material.NAME_TAG)){
            Optional<Tag> tag = TagParser.parse(itemInMainHand);
            if(tag.isPresent()){
                if(TagRegister.get(player) != null){
                    ChatUtils.send(player,"&7You are already applying a tag, please use the first tag, right click with an empty hand, or wait 15 seconds for it to time out!");
                    return true;
                }
                initiateApplication(tag.get(), ItemUtils.oneOf(itemInMainHand), player);
                return true;
            }
        } else {
            TagApplication application = TagRegister.get(player);
            if(application != null){
                apply(player, application);
                return true;
            }
        }
        return false;
    }

    private void initiateApplication(Tag tag, ItemStack item, final Player player){
        TagApplication application = TagApplicationBuilder.create()
                .forPlayer(player)
                .withCounter(BASE_COUNTER)
                .withTag(tag)
                .withTagItem(item)
                .build();
        TagRegister.register(player, application);
        ChatUtils.send(player, tag.getDisplayName() + "&8: &7You selected a " + tag.getDisplayName() + "&7. " + tag.getInstructions());
        player.getInventory().removeItem(item);
        RepeatingTaskUtils.everySeconds(1, () -> TagRegister.countdown(player), plugin);
    }

    private void apply(Player player, TagApplication application){
        ItemStack heldItem =  player.getInventory().getItemInMainHand();
        if(heldItem == null || heldItem.getType().equals(Material.AIR)){
            application.returnTag(player);
            TagRegister.remove(player);
            return;
        }
        if(application.getTag().apply(player.getInventory().getItemInMainHand(), application)){
            TagRegister.remove(player);
        }
    }

}
