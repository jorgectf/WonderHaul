package io.github.winterbear.wintercore.wonderhaul.blockstorage;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by WinterBear on 24/05/2020.
 */
@Entity
@Table(name="WH_BlockMeta")
public class BlockMetadata {

    private String worldName;

    private double x;

    private double y;

    private double z;

    private PersistedItem internalItem;

    private boolean drops = true;

    private String type;

    private Map<String, String> properties;

    private Map<String, PersistedInventory> customInventories;

    private Integer counter;

    public BlockMetadata(){
        //Don't use this, it's just for Hibernate
    }

    public BlockMetadata(Block block, ItemStack item, String type){
        Location location = block.getLocation();
        this.worldName = location.getWorld().getName();
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
        this.internalItem = new PersistedItem(item);
        this.type = type;
        this.customInventories = new HashMap<>();
    }

    @Id
    @Column(name="location",length = 50)
    public String getLocationReference(){
        return worldName + "|" + x + "|" + y + "|" + z;
    }

    public void setLocationReference(String reference){
        String[] components = reference.split("\\|");
        worldName = components[0];
        x = Double.parseDouble(components[1]);
        y = Double.parseDouble(components[2]);
        z = Double.parseDouble(components[3]);
    }

    @Transient
    public Location getInternalLocation(){

        World world = Bukkit.getServer().getWorld(worldName);
        if (world == null) {
            ChatUtils.warn("No world by name of " + worldName + " could be loaded.");
            return null;
        }
        return new Location(world, x, y, z);
    }

    @Transient
    public ItemStack getInternalItem() {
        return internalItem.getInternalItem();
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "internal_item_id", referencedColumnName = "id")
    public PersistedItem getSerialisedInternalItem(){
        return this.internalItem;
    }

    public void setSerialisedInternalItem(PersistedItem itemHolder){
        this.internalItem = itemHolder;
    }

    @Column(name="drops")
    public boolean getDrops() {
        return drops;
    }

    public void setDrops(boolean drops) {
        this.drops = drops;
    }

    @Column(name="type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapKey(name="reference")
    public Map<String, PersistedInventory> getCustomInventories() {
        return customInventories;
    }

    @Transient
    public Optional<PersistedInventory> getInventory(String reference){
        if(customInventories.containsKey(reference)){
            return Optional.of(customInventories.get(reference));
        } else {
            return Optional.empty();
        }
    }

    public void setCustomInventory(String reference, PersistedInventory inventory){
        customInventories.put(reference, inventory);
    }

    public void setCustomInventories(Map<String, PersistedInventory> customInventories) {
        this.customInventories = customInventories;
    }

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name="WH_BlockMeta_Props", joinColumns=@JoinColumn(name="block_location"))
    @MapKeyColumn(name="property_name", length = 50)
    @Column(name="property_value")
    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    @Transient
    public void setProperty(String name, String value){
        if(properties == null){
            properties = new HashMap<>();
        }
        properties.put(name, value);
    }

    @Transient
    public Optional<String> getProperty(String name){
        if(properties == null){
            properties = new HashMap<>();
        }
        if(properties.containsKey(name)){
            return Optional.ofNullable(properties.get(name));
        } else {
            return Optional.empty();
        }
    }
}
