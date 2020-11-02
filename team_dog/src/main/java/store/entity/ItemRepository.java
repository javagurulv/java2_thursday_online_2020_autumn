package store.entity;

import java.util.List;

//@Repository extends JpaRepository<Item, Long>
public interface ItemRepository {

    List<Item> getAll();
}
