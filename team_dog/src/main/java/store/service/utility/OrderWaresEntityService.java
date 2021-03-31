package store.service.utility;

import store.entity.items.Item;

import java.util.List;

public interface OrderWaresEntityService {

    List<Long> transformItemIds(List<Item> items);

    List<Item> transformItemsFromIds(List<Long> itemIds);
}
