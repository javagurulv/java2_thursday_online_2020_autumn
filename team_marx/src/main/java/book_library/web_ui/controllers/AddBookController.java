package book_library.web_ui.controllers;

import book_library.core.requests.Book.AddBookRequest;
import book_library.core.responses.Book.AddBookResponse;
import book_library.core.services.Book.AddBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddBookController {

    @Autowired
    private AddBookService addBookService;


    @GetMapping(value = "/addBookToList")
    public String showAddBookPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddBookRequest());
        return "addBookToList";
    }

    @PostMapping("/addBookToList")
    public String processAddBookRequest(@ModelAttribute(value = "request") AddBookRequest request, ModelMap modelMap) {
        AddBookResponse response = addBookService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "addBookToList";
        } else {
            return "redirect:/";
        }
    }

}
