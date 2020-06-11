package io.github.winterbear.wintercore.wonderhaul.Tags;

import io.github.winterbear.wintercore.SpigotTesting.SpigotTest;
import io.github.winterbear.wintercore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.Damageable;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by WinterBear on 06/06/2019.
 */
@Ignore
public class TagApplicatorTest extends SpigotTest {

    private TagApplicationListener target = new TagApplicationListener(mockJavaPlugin());

    @Test
    public void testNoInteractionWhenHeldItemNull(){
        Player mockPlayer = mockPlayer(mockInventory());
        PlayerInteractEvent mockEvent = mockEvent(Action.RIGHT_CLICK_AIR, mockPlayer);

        target.onInteractEvent(mockEvent);

        verify(mockPlayer, never()).sendMessage(anyString());
    }

    @Test
    public void testNoInteractionWhenHeldItemAir(){
        PlayerInventory inventory = mockInventory();
        when(inventory.getItemInMainHand()).thenReturn(new ItemStack(Material.AIR));
        Player mockPlayer = mockPlayer(inventory);
        PlayerInteractEvent mockEvent = mockEvent(Action.RIGHT_CLICK_AIR, mockPlayer);

        target.onInteractEvent(mockEvent);

        verify(mockPlayer, never()).sendMessage(anyString());
    }

    @Test
    public void testNoInteractionWhenHeldItemMat(){
        PlayerInventory inventory = mockInventory();
        ItemStack tag = ItemBuilder.createItem("Test", Material.STONE);
        when(inventory.getItemInMainHand()).thenReturn(tag);
        Player mockPlayer = mockPlayer(inventory);
        PlayerInteractEvent mockEvent = mockEvent(Action.RIGHT_CLICK_AIR, mockPlayer);

        target.onInteractEvent(mockEvent);

        verify(mockPlayer, never()).sendMessage(anyString());
    }

    @Test
    public void testInteractionWhenHeldItemRepairTag(){
        PlayerInventory inventory = mockInventory();
        ItemStack tag = ItemBuilder.createItem("Repair Tag", "&7Tag&8: &7Repair Tag", Material.NAME_TAG);
        when(inventory.getItemInMainHand()).thenReturn(tag);
        Player mockPlayer = mockPlayer(inventory);
        PlayerInteractEvent mockEvent = mockEvent(Action.RIGHT_CLICK_AIR, mockPlayer);

        target.onInteractEvent(mockEvent);

        verify(mockPlayer, times(1)).sendMessage("§aRepair Tag§8: §7You selected a §aRepair Tag§7. §7Rick click a damaged piece of equipment to repair it!");
        assertThat(TagRegister.get(mockPlayer)).extracting("counter", "tag").contains(15, Tags.get("Repair Tag"));
        assertThat(TagRegister.get(mockPlayer).getTagItem()).isEqualToComparingFieldByField(tag);

    }

    @Test
    public void testSecondInteractionWhenHeldItemRepairTag() throws InterruptedException {
        PlayerInventory inventory = mockInventory();
        ItemStack tag = ItemBuilder.createItem("Repair Tag", "&7Tag&8: &7Repair Tag", Material.NAME_TAG);
        when(inventory.getItemInMainHand()).thenReturn(tag);
        Player mockPlayer = mockPlayer(inventory);
        PlayerInteractEvent mockEvent = mockEvent(Action.RIGHT_CLICK_AIR, mockPlayer);

        target.onInteractEvent(mockEvent);

        ItemStack equipment = ItemBuilder.createItem("My Tool", Material.DIAMOND_PICKAXE);
        ((Damageable) equipment.getItemMeta()).setDamage(10);
        when(inventory.getItemInMainHand()).thenReturn(equipment);

        assertThat(((Damageable) equipment.getItemMeta()).getDamage()).isEqualTo(10);

        Thread.sleep(1001l);
        target.onInteractEvent(mockEvent);

        assertThat(((Damageable) equipment.getItemMeta()).hasDamage()).isFalse();
    }

    @Test
    public void testInteractionWhenHeldItemBoostTag(){
        PlayerInventory inventory = mockInventory();
        ItemStack tag = ItemBuilder.createItem("Boost Tag", "&7Tag&8: &7Boost Tag", Material.NAME_TAG);
        when(inventory.getItemInMainHand()).thenReturn(tag);
        Player mockPlayer = mockPlayer(inventory);
        PlayerInteractEvent mockEvent = mockEvent(Action.RIGHT_CLICK_AIR, mockPlayer);

        target.onInteractEvent(mockEvent);

        verify(mockPlayer, times(1)).sendMessage("§cBoost Tag§8: §7You selected a §cBoost Tag§7. §7Use on an enchanted item to boost the level of one of its enchantments!");
        assertThat(TagRegister.get(mockPlayer)).extracting("counter", "tag").contains(15, Tags.get("Boost Tag"));
        assertThat(TagRegister.get(mockPlayer).getTagItem()).isEqualToComparingFieldByField(tag);
    }

    @Test
    public void testSecondInteractionWhenHeldItemBoostTag() throws InterruptedException {
        PlayerInventory inventory = mockInventory();
        ItemStack tag = ItemBuilder.createItem("Boost Tag", "&7Tag&8: &7Boost Tag", Material.NAME_TAG);
        when(inventory.getItemInMainHand()).thenReturn(tag);
        Player mockPlayer = mockPlayer(inventory);
        PlayerInteractEvent mockEvent = mockEvent(Action.RIGHT_CLICK_AIR, mockPlayer);

        target.onInteractEvent(mockEvent);

        ItemStack equipment = spy(ItemBuilder.createItem("My Tool", Material.DIAMOND_PICKAXE));
        Enchantment testEnchant = spy(Enchantment.DAMAGE_ARTHROPODS);
        doReturn(3).when(testEnchant).getMaxLevel();
        doReturn(1).when(testEnchant).getStartLevel();
        doReturn(true).when(testEnchant).canEnchantItem(any(ItemStack.class));
        doReturn("Bane of Arthropods").when(testEnchant).toString();
        Map<Enchantment, Integer> enchants = new HashMap<>();
        enchants.put(testEnchant, 1);
        doReturn(enchants).when(equipment).getEnchantments();
        doAnswer(i -> {
            enchants.put(i.getArgument(0), i.getArgument(1));
            return null;
        }).when(equipment).addEnchantment(any(Enchantment.class), anyInt());
        when(inventory.getItemInMainHand()).thenReturn(equipment);

        Thread.sleep(1001l);
        target.onInteractEvent(mockEvent);

        assertThat(equipment.getEnchantments().get(testEnchant)).isEqualTo(2);
    }



    private Player mockPlayer(PlayerInventory inventory){
        Player player = mock(Player.class);
        when(player.getInventory()).thenReturn(inventory);
        doAnswer(i -> {logPlayerChat(i.getArgument(0)); return null;}).when(player).sendMessage(anyString());
        return player;

    }

    private void logPlayerChat(String message){
        System.out.println("System -> Player: " + message);
    }

    private PlayerInventory mockInventory(){
        PlayerInventory inventory = mock(PlayerInventory.class);

        return inventory;
    }

}