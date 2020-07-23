package io.github.winterbear.wintercore.wonderhaul.blockstorage;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.persistence.*;
import java.util.Map;

/**
 * Created by WinterBear on 24/05/2020.
 */
@Entity
@Table(name="WH_BlockMeta")
public class BlockMetadata {

    private Location location;

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
        this.location = block.getLocation();
        this.internalItem = new PersistedItem(item);
        this.type = type;
    }

    @Id
    @Column(name="location",length = 50)
    public String getLocationReference(){
        return location.getWorld().getName() + "|" + location.getBlockX() + "|" + location.getBlockY() + "|" + location.getBlockZ();
    }

    public void setLocationReference(String reference){
        String[] components = reference.split("\\|");
        World world = Bukkit.getServer().getWorld(components[0]);
        double x = Double.parseDouble(components[1]);
        double y = Double.parseDouble(components[2]);
        double z = Double.parseDouble(components[3]);
        this.location = new Location(world, x, y, z);
    }

    @Transient
    public Location getInternalLocation(){ return location; }

    @Transient
    public ItemStack getInternalItem() {
        return internalItem.getInternalItem();
    }

    @OneToOne(cascade = CascadeType.ALL)
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

    @OneToMany(cascade = CascadeType.ALL)
    @MapKey(name="reference")
    public Map<String, PersistedInventory> getCustomInventories() {
        return customInventories;
    }

    public void setCustomInventories(Map<String, PersistedInventory> customInventories) {
        this.customInventories = customInventories;
    }

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name="WH_BlockMeta_Props", joinColumns=@JoinColumn(name="block_location"))
    @MapKeyColumn(name="property_name", length = 50)
    @Column(name="property_value")
    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
