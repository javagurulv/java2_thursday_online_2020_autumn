package store.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import store.dtos.OrderDTO;
import store.entity.items.Item;
import store.service.items.ItemService;
import store.service.orders.OrderService;
import store.service.resolver.ApiResolver;
import store.service.utility.PdfServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

@Controller
@RequestMapping("/script")
public class UtilityController {

    @Autowired
    ApiResolver apiResolver;

    @PostMapping("/addToBasket")
    @ResponseBody
    public String addToBasket(@RequestParam("itemId") Long itemId){
        return apiResolver.addItemToBasket(itemId);
    }

    @GetMapping("/getPdf")
    @ResponseBody
    public ResponseEntity<Resource> getPdf(){
        Resource file = apiResolver.getPdfOrder();
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
