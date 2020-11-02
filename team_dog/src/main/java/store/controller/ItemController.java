package store.controller;

import store.entity.Item;
import store.entity.types.ItemType;
import store.service.ItemRetrievalService;
import store.service.ItemRetrievalServiceImpl;

import java.util.List;
import java.util.Optional;

//@Controller
public class ItemController {

    ItemRetrievalService itemRetrievalService = new ItemRetrievalServiceImpl();

//    @Get
    public List<Item> getAll() {
        return itemRetrievalService.getAll();
    }

//    @Get
    public Optional<Item> getItemByType(ItemType requestParam){
        return itemRetrievalService.getItemByType(requestParam);
    }
}
