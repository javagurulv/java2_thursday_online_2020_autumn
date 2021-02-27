package book_library.web_ui.controllers;

import book_library.core.requests.Book.GetAllBooksRequest;
import book_library.core.responses.Book.GetAllBooksResponse;
import book_library.core.services.Book.GetAllBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllBooksController {

    @Autowired
    private GetAllBooksService getAllBooksService;


    @GetMapping(value = "/showAllBooks")
    public String showAllBooks(ModelMap modelMap) {
        GetAllBooksResponse response = getAllBooksService.execute(
                new GetAllBooksRequest()
        );
        modelMap.addAttribute("books", response.getBooks());
        return "/showAllBooks";
    }
}
