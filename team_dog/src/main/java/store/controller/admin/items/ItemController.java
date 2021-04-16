package store.controller.admin.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.dtos.ItemDTO;
import store.service.resolver.ApiResolver;

@Controller
@RequestMapping("/admin/items/{id}")
public class ItemController {

    @Autowired
    ApiResolver apiResolver;

    @GetMapping
    public String listItemsByTypes(@PathVariable Integer id, ItemDTO i, Model model){
        model.addAttribute("itemDtos", apiResolver.getByItemTypeId(id));
        return "admin_items";
    }

    @PostMapping  //todo make select dropdown for item types input model
    public String addItem(@PathVariable Integer id, ItemDTO itemDto, Model model){
        apiResolver.addNewItem(Long.valueOf(id), itemDto);
        model.addAttribute("itemDtos", apiResolver.getByItemTypeId(id));
        return "redirect:/admin/items/" + id;
    }

    @GetMapping("/edit")
    public String openEditItemMenu(@PathVariable Long id, Model model){
        model.addAttribute("itemDto", apiResolver.getItemById(id));
        return "admin_item_edit_form";
    }

    @PostMapping("/edit")
    public String editItem(@PathVariable Long id, ItemDTO itemDTO){
        return "redirect:/admin/items/" + apiResolver.updateItem(id, itemDTO);
    }

    @PostMapping("/delete")
    public String deleteItem(@PathVariable Long id){
        return "redirect:/admin/items/" + apiResolver.deleteItemById(id);
    }
}
