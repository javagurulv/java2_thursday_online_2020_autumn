package store.dtos;

import store.entity.items.Item;

public class ItemDTO {

    private Long id;
    private String name;
    private Integer quantity;
    private Integer price;
    private String description;
    private String typeName;

    public ItemDTO() {

    }

    public ItemDTO(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.quantity = item.getQuantity();
        this.price = item.getPrice();
        this.description = item.getDescription();
        this.typeName = item.getItemType().getTypeName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
