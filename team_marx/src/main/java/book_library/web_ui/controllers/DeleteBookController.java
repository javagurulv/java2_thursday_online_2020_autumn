package book_library.web_ui.controllers;

import book_library.core.requests.Book.RemoveBookRequest;
import book_library.core.responses.Book.RemoveBookResponse;
import book_library.core.services.Book.RemoveBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteBookController {

    @Autowired
    private RemoveBookService removeBookService;

    @GetMapping(value = "/deleteBookFromList")
    public String showDeleteBookPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new RemoveBookRequest());
        return "deleteBookFromList";
    }

    @PostMapping("/deleteBookFromList")
    public String processDeleteBookRequest(@ModelAttribute(value = "request") RemoveBookRequest request, ModelMap modelMap) {
        RemoveBookResponse response = removeBookService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "deleteBookFromList";
        } else {
            return "redirect:/";
        }
    }
}
