package store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import store.entity.items.ItemType;


public interface ItemTypeRepository extends JpaRepository<ItemType, Long> {

}
