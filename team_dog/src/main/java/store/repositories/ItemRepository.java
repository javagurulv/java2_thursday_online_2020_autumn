package store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
