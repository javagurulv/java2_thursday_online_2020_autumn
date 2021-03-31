package store.service.resolver;

import org.springframework.core.io.Resource;
import store.dtos.ItemDTO;
import store.dtos.ItemTypeDto;
import store.entity.items.Item;
import store.entity.items.ItemType;
import store.entity.orders.Order;

import java.util.List;

public interface ApiResolver {

    List<ItemDTO> getByItemTypeId(Integer itemTypeId);

    List<ItemTypeDto> getAllItemTypes();

    ItemDTO getItemById(Long id);

    List<Item> getBasket();

    void removeFromBasket(Long itemId);

    void addNewItem(Long id, ItemDTO itemDto);

    Long updateItem(Long id, ItemDTO itemDTO);

    Long deleteItemById(Long id);

    void addNewItemType(ItemType itemType);

    ItemTypeDto getItemTypeById(Long id);

    void updateItemType(Long id, ItemTypeDto itemTypeDto);

    void deleteItemTypeById(Long id);

    List<Order> getAllOrders();

    String addItemToBasket(Long itemId);

    Resource getPdfOrder();
}
