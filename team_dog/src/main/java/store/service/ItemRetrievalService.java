package store.service;

import store.entity.Item;
import store.entity.types.ItemType;

import java.util.List;
import java.util.Optional;

public interface ItemRetrievalService {

    List<Item> getAll();
    Optional<Item> getItemByType(ItemType type);
}
