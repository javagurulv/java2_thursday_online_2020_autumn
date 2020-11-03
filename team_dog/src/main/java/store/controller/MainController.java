package store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import store.entity.Item;
import store.entity.types.ItemType;
import store.mockSpringClasses.JpaMock;
import store.service.ItemRetrievalService;
import store.service.ItemRetrievalServiceImpl;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class MainController {

    JpaMock itemRetrievalService = new JpaMock();

    @GetMapping(value = {"/", "/home"})
    @ResponseBody
    public List<Item> getAll() {
        return itemRetrievalService.getAll();
    }

    @GetMapping("/showitem/{type}")
    public Optional<Item> getItemByType(@PathVariable  ItemType type){
        return null;
    }
}
