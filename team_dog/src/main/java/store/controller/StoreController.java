package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import store.service.resolver.ApiResolver;



@Controller
@RequestMapping("/")
public class StoreController {

    @Autowired
    ApiResolver apiResolver;

    @GetMapping(value = {"/", "/home"})
    public String getHomePage(Model model) {
        model.addAttribute("itemTypeDtos", apiResolver.getAllItemTypes());
        return "home";
    }

    @GetMapping("/wares")
    public String getWarePage(@RequestParam("type") Integer id, Model model){
        model.addAttribute("itemTypeDtos", apiResolver.getAllItemTypes());
        model.addAttribute("itemDtos", apiResolver.getByItemTypeId(id));
        return "wares";
    }

    @GetMapping("/basket")
    public String showBasket(Model model){
        model.addAttribute("itemTypeDtos", apiResolver.getAllItemTypes());
        model.addAttribute("basket", apiResolver.getBasket());
        return "basket";
    }

    @PostMapping("/basket")    //TODO make DeleteMapping request
    public String removeFromBasket(@RequestParam("itemId") Long itemId, Model model){
        apiResolver.removeFromBasket(itemId);
        model.addAttribute("itemTypeDtos", apiResolver.getAllItemTypes());
        model.addAttribute("basket", apiResolver.getBasket());
        return "basket";
    }

}
