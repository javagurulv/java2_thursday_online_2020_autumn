package store.entity;

import store.entity.types.ItemType;

//@Entity
public class Item {

    private Long id;    // should be auto increment in DB
    private String name;
    private ItemType itemType;
    private Integer quantity;
    private String description;

    private static Long idCount = 0L;

    public Item(String name, ItemType type, Integer qty, String desc){
        this.id = idCount++;
        this.name = name;
        this.itemType = type;
        this.quantity = qty;
        this.description = desc;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }
}
