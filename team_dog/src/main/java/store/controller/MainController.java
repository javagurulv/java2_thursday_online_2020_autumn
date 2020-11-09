package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import store.service.ItemRetrievalService;


@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private ItemRetrievalService itemRetrievalService;

    @GetMapping(value = {"/", "/home"})
    public String getHomePage() {
        return "home-page";
    }

    @GetMapping("/wares")
    public String getWarePage(@RequestParam("type") String wareType){
        System.out.println("ware page test : " + wareType);
        return "home-page";
    }
}
