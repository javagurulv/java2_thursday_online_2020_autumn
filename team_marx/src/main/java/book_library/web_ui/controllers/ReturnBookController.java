package book_library.web_ui.controllers;

import book_library.core.requests.ReaderBook.ReturnBookRequest;
import book_library.core.responses.ReaderBook.ReturnBookResponse;
import book_library.core.services.ReaderBooks.ReturnBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReturnBookController {

    @Autowired
    private ReturnBookService returnBookService;

    @GetMapping(value = "/returnBook")
    public String returnBookPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new ReturnBookRequest());
        return "returnBook";
    }

    @PostMapping("/returnBook")
    public String returnBookRequest(@ModelAttribute(value = "request") ReturnBookRequest request, ModelMap modelMap) {
        ReturnBookResponse response = returnBookService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "returnBook";
        } else {
            return "redirect:/";
        }
    }

}
