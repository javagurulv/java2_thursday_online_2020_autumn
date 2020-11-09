package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import store.entity.Item;
import store.entity.types.ItemType;
import store.service.ItemRetrievalService;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private ItemRetrievalService itemRetrievalService;

    @Value("${welcome.message:msg_not_found}")
    private String message;


    @GetMapping(value = {"/", "/home"})
    @ResponseBody
    public String getAll() {
        itemRetrievalService.saveExampleItem();
        return message;
    }

    @GetMapping("/item/{type}")
    public Optional<Item> getItemByType(@PathVariable  ItemType type){
        return null;
    }
}
