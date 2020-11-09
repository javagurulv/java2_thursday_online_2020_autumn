package store.entity.types;

@Deprecated
public enum ItemType {
    SHIRT(1, "soft cotton"),
    JACKET(2, "leather"),
    TROUSERS(3, "jeans"),
    SOCKS(4, "hard cotton");

    private Integer id;
    private String material;


    ItemType(Integer id, String material){
        this.id = id;
        this.material = material;
    }
}
