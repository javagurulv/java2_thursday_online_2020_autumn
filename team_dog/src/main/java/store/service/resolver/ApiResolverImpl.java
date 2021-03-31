package store.service.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import store.dtos.ItemDTO;
import store.dtos.ItemTypeDto;
import store.entity.items.Item;
import store.entity.items.ItemType;
import store.entity.orders.Order;
import store.service.items.ItemService;
import store.service.items.ItemTypeService;
import store.service.orders.OrderService;
import store.service.utility.PdfService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiResolverImpl implements ApiResolver {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemTypeService itemTypeService;
    @Autowired
    OrderService orderService;
    @Autowired
    PdfService pdfService;

    @Override
    public List<ItemDTO> getByItemTypeId(Integer itemTypeId) {
        List<Item> items = itemService.getByItemTypeId(itemTypeId);
        return items.stream().map(ItemDTO::new).collect(Collectors.toList());
    }

    @Override
    public ItemDTO getItemById(Long id) {
        return new ItemDTO(itemService.getById(id));
    }

    @Override
    public List<ItemTypeDto> getAllItemTypes() {
        List<ItemType> itemTypes = itemTypeService.getAllItemTypes();
        return itemTypes.stream().map(ItemTypeDto::new).collect(Collectors.toList());
    }

    @Override
    public List<Item> getBasket() {
        return orderService.getBasket();
    }

    @Override
    public void removeFromBasket(Long itemId) {
        orderService.removeFromBasket(itemId);
    }

    @Override // todo make native query so other service will no be used
    public void addNewItem(Long id, ItemDTO itemDto) {
        ItemType type = itemTypeService.getById(id);
        Item item = new Item(itemDto.getName(), type, itemDto.getQuantity(), itemDto.getPrice(), itemDto.getDescription());
        itemService.save(item);
    }

    @Override // todo make native query so other service will no be used
    public Long updateItem(Long id, ItemDTO itemDTO) {
        Item item = itemService.getById(id);
        updateItem(item, itemDTO);
        itemService.save(item);
        return item.getItemType().getId();
    }



    @Override
    public Long deleteItemById(Long id) {
        return itemService.deleteById(id);
    }

    @Override
    public void addNewItemType(ItemType itemType) {
        itemTypeService.saveItemType(itemType);
    }

    @Override
    public ItemTypeDto getItemTypeById(Long id) {
        ItemType itemType = itemTypeService.getById(id);
        return new ItemTypeDto(itemType);
    }

    @Override
    public void updateItemType(Long id, ItemTypeDto itemTypeChanges) {
        ItemType itemType = itemTypeService.getById(id);
        updateItemType(itemType, itemTypeChanges);
        itemTypeService.saveItemType(itemType);
    }

    @Override
    public void deleteItemTypeById(Long id) {
        itemTypeService.deleteById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderService.getAllOrder();
    }

    @Override
    public String addItemToBasket(Long itemId) {
        Item item = itemService.getById(itemId);
        orderService.addToBasket(item);
        String success = "Item " + item.getName() + " successfully added to basket";
        return success;
    }

    @Override
    public Resource getPdfOrder() {
        return pdfService.handleRequest();
    }

    private void updateItem(Item itemToUpdate, ItemDTO changes) {
        itemToUpdate.setName(changes.getName());
        itemToUpdate.setPrice(changes.getPrice());
        itemToUpdate.setQuantity(changes.getQuantity());
        itemToUpdate.setDescription(changes.getDescription());
    }

    private void updateItemType(ItemType typeToUpdate, ItemTypeDto changes){
        typeToUpdate.setTypeName(changes.getTypeName());
        typeToUpdate.setMaterial(changes.getMaterial());
        typeToUpdate.setDescription(changes.getDescription());
    }
}
