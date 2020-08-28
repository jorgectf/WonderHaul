package io.github.winterbear.wintercore.wonderhaul.blockstorage;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="WH_PersistedInventory")
public class PersistedInventory {

    private Long id;

    private String reference;

    private List<PersistedItem> items;

    public PersistedInventory(){

    }

    public PersistedInventory(String reference){
        this.reference = reference;
    }

    @Id
    @GeneratedValue
    @Column(name="id")
    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="reference")
    public String getReference(){
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name="WH_PersistedInv_Item",
            joinColumns = @JoinColumn( name="inventory_id"),
            inverseJoinColumns = @JoinColumn( name="item_id")
    )
    public List<PersistedItem> getItems(){
        return this.items;
    }


    public void setItems(List<PersistedItem> items) {
        this.items = items;
    }
}
