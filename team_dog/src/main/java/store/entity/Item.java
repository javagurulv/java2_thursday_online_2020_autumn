package store.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "items_all")
//@Getter
public class Item {

    @Id @GeneratedValue (strategy = IDENTITY) private Long id;
    @Column(name = "itemname") private String name;
    @Column(name = "itemtype") private Integer itemType;     // should make enumType + converteer to store only id in DB
    @Column(name = "qty") private Integer quantity;
     private String description;

    public Item() {

    }

    public Item(String name, Integer itemType, Integer quantity, String description) {
        this.name = name;
        this.itemType = itemType;
        this.quantity = quantity;
        this.description = description;
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

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
