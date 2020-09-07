package io.github.winterbear.wintercore.wonderhaul.blockstorage;

import io.github.winterbear.wintercore.database.ItemStackSerializer;
import org.bukkit.inventory.ItemStack;

import javax.persistence.*;
import java.io.IOException;

@Entity
@Table(name="WH_PersistedItem")
public class PersistedItem {


    private Long id;

    private ItemStack item;

    public PersistedItem(){
        //Don't use this, it's just for Hibernate
    }

    public PersistedItem(ItemStack item){
        this.item = item;
    }

    @Id
    @GeneratedValue
    @Column(name="id")
    public Long getId(){
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="item",length=10000,columnDefinition="Text")
    public String getItem(){
        return ItemStackSerializer.serialiseItem(item);
    }

    public void setItem(String item) throws IOException, ClassNotFoundException {
        this.item = ItemStackSerializer.deserialiseItem(item);
    }

    @Transient
    public ItemStack getInternalItem(){
        return item;
    }




}
