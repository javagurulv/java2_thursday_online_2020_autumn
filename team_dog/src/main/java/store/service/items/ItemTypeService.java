package store.service.items;

import store.entity.items.ItemType;

import java.util.List;

public interface ItemTypeService {


    List<ItemType> getAllItemTypes();

    void deleteById(Long id);

    void saveItemType(ItemType itemType);

    ItemType getById(Long id);


}
