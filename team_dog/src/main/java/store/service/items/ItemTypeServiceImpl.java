package store.service.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.entity.items.ItemType;
import store.repositories.ItemTypeRepository;

import java.util.List;

@Service
public class ItemTypeServiceImpl implements ItemTypeService
{

    @Autowired
    ItemTypeRepository repository;

    @Override
    public List<ItemType> getAllItemTypes() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void saveItemType(ItemType itemType) {
        repository.save(itemType);

    }

    @Override
    public ItemType getById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
