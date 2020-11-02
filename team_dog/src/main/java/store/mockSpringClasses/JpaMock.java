package store.mockSpringClasses;

import store.entity.Item;
import store.entity.ItemRepository;

import java.util.ArrayList;
import java.util.List;

import static store.entity.types.ItemType.*;

public class JpaMock implements ItemRepository {

    private List<Item> mySmallDataBase = new ArrayList<Item>() {
        {
            add(new Item("Simple Shirt", SHIRT, 6, "Good quality"));
            add(new Item("Leather Jacket", JACKET, 3, "Very Good quality"));
            add(new Item("Simple Jeans", TROUSERS, 10, "jeans like jeans"));
            add(new Item("Sport Socks", SOCKS, 23, "cotton socks"));
        }
    };


    public List<Item> getAll() {
        return mySmallDataBase;
    }

}

