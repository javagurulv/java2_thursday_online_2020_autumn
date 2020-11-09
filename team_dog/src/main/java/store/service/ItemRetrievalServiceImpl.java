package store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.entity.Item;
import store.repositories.ItemRepository;


@Service
public class ItemRetrievalServiceImpl implements ItemRetrievalService {


    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void saveExampleItem(){
        itemRepository.save(new Item("name",1,5,"text"));
    }
}
