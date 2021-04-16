package store.service.resolver;

import org.springframework.core.io.Resource;
import store.dtos.ItemDTO;
import store.dtos.ItemTypeDTO;
import store.dtos.OrderDTO;
import store.entity.items.Item;
import store.entity.items.ItemType;
import store.entity.orders.Order;

import java.util.List;

public interface ApiResolver {

    List<ItemDTO> getByItemTypeId(Integer itemTypeId);

    List<ItemTypeDTO> getAllItemTypes();

    ItemDTO getItemById(Long id);

    List<Item> getBasket();

    void removeFromBasket(Long itemId);

    void addNewItem(Long id, ItemDTO itemDto);

    Long updateItem(Long id, ItemDTO itemDTO);

    Long deleteItemById(Long id);

    void addNewItemType(ItemType itemType);

    ItemTypeDTO getItemTypeById(Long id);

    void updateItemType(Long id, ItemTypeDTO itemTypeDto);

    void deleteItemTypeById(Long id);

    List<Order> getAllOrders();

    String addItemToBasket(Long itemId);

    Resource getPdfOrder();

    Long createNewOrder(OrderDTO orderDTO);

    OrderDTO getOrder(Long id);

    List<Item> getOrderItems(Long id);
}
