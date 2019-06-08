package io.github.winterbear.wintercore.Tags;

import io.github.winterbear.wintercore.utils.ChatUtils;
import io.github.winterbear.wintercore.utils.RepeatingTaskUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

/**
 * Created by WinterBear on 05/06/2019.
 */
public class TagApplicator implements Listener {

    private static final int BASE_COUNTER = 15;

    private final JavaPlugin plugin;

    public TagApplicator(JavaPlugin plugin){
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private boolean eventIsLeftClickEvent(PlayerInteractEvent event){
        return event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK);
    }


    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event) {
        if(eventIsLeftClickEvent(event)){
            begin(event.getPlayer());
        }
    }

    private void begin(Player player){
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        if(itemInMainHand != null && itemInMainHand.getType().equals(Material.NAME_TAG)){
            Optional<Tag> tag = TagParser.parse(itemInMainHand);
            if(tag.isPresent()){
                if(TagRegister.get(player) != null){
                    ChatUtils.send(player,"&7You are already applying a tag, please use the first tag, right click with an empty hand, or wait 15 seconds for it to time out!");
                    return;
                }
                initiateApplication(tag.get(), itemInMainHand, player);
            }
        } else {
            TagApplication application = TagRegister.get(player);
            if(application != null){
                apply(player, application);
            }
        }
    }

    private void initiateApplication(Tag tag, ItemStack item, final Player player){
        TagApplication application = TagApplicationBuilder.create()
                .withCounter(BASE_COUNTER)
                .withTag(tag)
                .withTagItem(item)
                .build();
        TagRegister.register(player, application);
        ChatUtils.send(player, tag.getDisplayName() + "&8: &7You selected a " + tag.getDisplayName() + "&7. " + tag.getInstructions());
        RepeatingTaskUtils.everySeconds(1, () -> TagRegister.countdown(player), plugin);
    }

    private void apply(Player player, TagApplication application){
        ItemStack heldItem =  player.getInventory().getItemInMainHand();
        if(heldItem == null || heldItem.getType().equals(Material.AIR)){
            application.returnTag(player);
            return;
        }
        if(application.getTag().apply(player.getInventory().getItemInMainHand(), player)){
            TagRegister.remove(player);
        }
    }

}
