package io.github.winterbear.wintercore.Tags;

import io.github.winterbear.wintercore.JanusGates.ItemBuilder;
import io.github.winterbear.wintercore.SpigotTesting.SpigotTest;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by WinterBear on 06/06/2019.
 */
public class TagApplicatorTest extends SpigotTest {

    private TagApplicator target = new TagApplicator(mockJavaPlugin());

    @Test
    public void testNoInteractionWhenHeldItemNull(){
        Player mockPlayer = mockPlayer(mockInventory());
        PlayerInteractEvent mockEvent = mockEvent(Action.LEFT_CLICK_AIR, mockPlayer);

        target.onInteractEvent(mockEvent);

        verify(mockPlayer, never()).sendMessage(anyString());
    }

    @Test
    public void testNoInteractionWhenHeldItemAir(){
        PlayerInventory inventory = mockInventory();
        when(inventory.getItemInMainHand()).thenReturn(new ItemStack(Material.AIR));
        Player mockPlayer = mockPlayer(inventory);
        PlayerInteractEvent mockEvent = mockEvent(Action.LEFT_CLICK_AIR, mockPlayer);

        target.onInteractEvent(mockEvent);

        verify(mockPlayer, never()).sendMessage(anyString());
    }

    @Test
    public void testNoInteractionWhenHeldItemMat(){
        PlayerInventory inventory = mockInventory();
        ItemStack tag = ItemBuilder.createItem("Test", Material.STONE);
        when(inventory.getItemInMainHand()).thenReturn(tag);
        Player mockPlayer = mockPlayer(inventory);
        PlayerInteractEvent mockEvent = mockEvent(Action.LEFT_CLICK_AIR, mockPlayer);

        target.onInteractEvent(mockEvent);

        verify(mockPlayer, never()).sendMessage(anyString());
    }

    @Test
    public void testNoInteractionWhenHeldItemTag(){
        PlayerInventory inventory = mockInventory();
        ItemStack tag = ItemBuilder.createItem("Repair Tag", "&7Tag&8: &7Repair Tag", Material.NAME_TAG);
        when(inventory.getItemInMainHand()).thenReturn(tag);
        Player mockPlayer = mockPlayer(inventory);
        PlayerInteractEvent mockEvent = mockEvent(Action.LEFT_CLICK_AIR, mockPlayer);

        target.onInteractEvent(mockEvent);

        verify(mockPlayer, times(1)).sendMessage("&aRepair Tag&8: &7You selected a &aRepair Tag&7.&7Rick click a damaged piece of equipment to repair it!");
        assertThat(TagRegister.get(mockPlayer)).extracting("counter", "tagItem", "tag").contains(15, tag, TagParser.tagMap.get("Repair Tag"));
    }



    private Player mockPlayer(PlayerInventory inventory){
        Player player = mock(Player.class);
        when(player.getInventory()).thenReturn(inventory);
        return player;

    }

    private PlayerInventory mockInventory(){
        PlayerInventory inventory = mock(PlayerInventory.class);

        return inventory;
    }

}