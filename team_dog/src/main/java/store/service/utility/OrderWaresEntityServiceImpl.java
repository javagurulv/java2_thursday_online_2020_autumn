package store.service.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.entity.items.Item;
import store.service.items.ItemService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderWaresEntityServiceImpl implements OrderWaresEntityService {

    @Autowired
    ItemService itemService;

    @Override
    public List<Long> transformItemIds(List<Item> items) {
        return items.stream().map(Item::getId).collect(Collectors.toList());
    }

    @Override
    public List<Item> transformItemsFromIds(List<Long> itemIds) {
        return itemService.getAllByIds(itemIds);
    }
}
