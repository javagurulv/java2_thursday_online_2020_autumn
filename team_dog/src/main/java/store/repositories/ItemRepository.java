package store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import store.entity.items.Item;
import store.entity.items.ItemType;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByItemType(ItemType itemType);

    @Query(value = "select * from items_All where itemType_id = :itemType", nativeQuery = true)
    List<Item> findByItemTypeId(@Param("itemType")Integer itemTypeId);

}
