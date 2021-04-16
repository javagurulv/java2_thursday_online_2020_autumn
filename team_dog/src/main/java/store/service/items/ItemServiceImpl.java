package store.service.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.entity.items.Item;
import store.repositories.ItemRepository;

import java.util.List;


@Service
public class ItemServiceImpl implements ItemService {


    @Autowired
    private ItemRepository repository;

    @Override
    public List<Item> getByItemTypeId(Integer itemTypeId) {
        return repository.findByItemTypeId(itemTypeId);
    }

    @Override
    public Item getById(Long itemId) {
        return repository.findById(itemId).orElse(null);
    }


    @Override
    public Long deleteById(Long id) {
        Item itemToDelete = repository.getOne(id);
        Long typeIdOfdeletedItem = itemToDelete.getItemType().getId();
        repository.delete(itemToDelete);
        return typeIdOfdeletedItem;
    }

    @Override
    public List<Item> getAllByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public void save(Item item) {
        repository.save(item);
    }
}
