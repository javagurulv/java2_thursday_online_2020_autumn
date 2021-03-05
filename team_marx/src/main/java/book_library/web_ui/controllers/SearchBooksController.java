package book_library.web_ui.controllers;

import book_library.core.requests.Book.AddBookRequest;
import book_library.core.requests.Book.SearchBooksRequest;
import book_library.core.responses.Book.AddBookResponse;
import book_library.core.responses.Book.SearchBooksResponse;
import book_library.core.services.Book.AddBookService;
import book_library.core.services.Book.SearchBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchBooksController {

    @Autowired
    private SearchBooksService searchBooksService;

    @GetMapping(value = "/searchBooks")
    public String showSearchBooksPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchBooksRequest());
        return "searchBooks";
    }

    @PostMapping("/searchBooks")
    public String processSearchBooksRequest(@ModelAttribute(value = "request") SearchBooksRequest request, ModelMap modelMap) {
        SearchBooksResponse response = searchBooksService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "searchBooks";
        } else {
            modelMap.addAttribute("books", response.getBooks());
            return "/searchBooksResults";
        }
    }
}
