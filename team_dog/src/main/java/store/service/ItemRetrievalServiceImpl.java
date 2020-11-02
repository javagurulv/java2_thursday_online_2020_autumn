package store.service;

import store.entity.Item;
import store.entity.ItemRepository;
import store.entity.types.ItemType;
import store.mockSpringClasses.JpaMock;

import java.util.List;
import java.util.Optional;

//@Service
public class ItemRetrievalServiceImpl implements ItemRetrievalService {

    private ItemRepository itemRepository = new JpaMock();

    @Override
    public List<Item> getAll() {
        return itemRepository.getAll();
    }

    @Override
    public Optional<Item> getItemByType(ItemType type) {
        return itemRepository.getAll().stream().filter(item -> item.getItemType().equals(type)).findFirst();
    }
}
