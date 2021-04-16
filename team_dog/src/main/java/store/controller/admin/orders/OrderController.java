package store.controller.admin.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.service.resolver.ApiResolver;

@Controller
@RequestMapping("/admin/orders")
public class OrderController {

    @Autowired
    ApiResolver apiResolver;

    @GetMapping
    public String getAllOrder(Model model){
        model.addAttribute("orders", apiResolver.getAllOrders());
        return "admin_orders";
    }
}
