package store.dtos;

import store.entity.items.ItemType;

public class ItemTypeDto {

    private Long id;
    private String typeName;
    private String material;
    private String description;

    public ItemTypeDto() {

    }

    public ItemTypeDto(ItemType itemType) {
        this.id = itemType.getId();
        this.typeName = itemType.getTypeName();
        this.material = itemType.getMaterial();
        this.description = itemType.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
