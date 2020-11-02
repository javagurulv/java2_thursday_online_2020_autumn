package store.entity.types;

public enum ItemType {
    SHIRT(1),
    JACKET(2),
    TROUSERS(3),
    SOCKS(4);

    private Integer id;


    ItemType(Integer id){
        this.id = id;
    }
}
