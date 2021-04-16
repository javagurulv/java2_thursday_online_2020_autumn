package store.controller.admin.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.dtos.ItemTypeDTO;
import store.entity.items.ItemType;
import store.service.resolver.ApiResolver;

@Controller
@RequestMapping("/admin/item/types")
public class ItemTypeController {

    @Autowired
    ApiResolver apiResolver;

    @GetMapping
    public String listItemTypes(ItemType i, Model model){
        model.addAttribute("itemTypeDtos", apiResolver.getAllItemTypes());
        return "admin_itemtypes";
    }

    @PostMapping
    public String addItemType(ItemType itemType){
        apiResolver.addNewItemType(itemType);
        return "redirect:/admin/item/types";
    }

    @GetMapping("/{id}/edit")
    public String openEditItemTypeMenu(@PathVariable Long id, Model model){
        model.addAttribute("itemTypeDto", apiResolver.getItemTypeById(id));
        return "admin_itemtype_edit_form";
    }

    @PostMapping("/{id}/edit")
    public String editItemType(@PathVariable Long id, ItemTypeDTO itemTypeDto){
        apiResolver.updateItemType(id, itemTypeDto);
        return "redirect:/admin/item/types";
    }

    @PostMapping("/{id}/delete")
    public String deleteItemType(@PathVariable Long id){
        apiResolver.deleteItemTypeById(id);
        return "redirect:/admin/item/types";
    }
}
