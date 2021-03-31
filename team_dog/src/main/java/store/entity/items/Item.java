package store.entity.items;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "itemsAll")
@Table(name = "items_all")
public class Item {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "itemname")
    private String name;

    @JoinColumn(name = "itemtype_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ItemType itemType;

    @Column(name = "qty")
    private Integer quantity;

    private Integer price;

    private String description;


    public Item() {

    }

    public Item(String name, ItemType itemType, Integer quantity, Integer price, String description) {
        this.name = name;
        this.itemType = itemType;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
