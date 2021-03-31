package store.service.items;

import store.dtos.ItemDTO;
import store.entity.items.Item;

import java.util.List;

public interface ItemService {

    List<Item> getByItemTypeId(Integer itemTypeId);

    Item getById(Long itemId);

    Long deleteById(Long id);

    List<Item> getAllByIds(List<Long> ids);

    void save(Item item);
}
