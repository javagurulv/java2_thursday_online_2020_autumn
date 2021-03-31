package store.entity.items;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name ="item_types")
public class ItemType {

    @Id
    @GeneratedValue(strategy = IDENTITY) private Long id;
    private String typeName;
    private String material;
    private String description;


    public ItemType() {
    }

    public ItemType(Long id, String typeName, String material, String description) {
        this.id = id;
        this.typeName = typeName;
        this.material = material;
        this.description = description;
    }

    public Long getId() {
        return id;
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
