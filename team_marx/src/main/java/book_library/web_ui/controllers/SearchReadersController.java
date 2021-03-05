package book_library.web_ui.controllers;

import book_library.core.requests.Book.SearchBooksRequest;
import book_library.core.requests.Reader.SearchReaderRequest;
import book_library.core.responses.Book.SearchBooksResponse;
import book_library.core.responses.Reader.SearchReadersResponse;
import book_library.core.services.Book.SearchBooksService;
import book_library.core.services.Reader.SearchReadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchReadersController {

    @Autowired
    private SearchReadersService searchReadersService;

    @GetMapping(value = "/searchReaders")
    public String showSearchReadersPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchReaderRequest());
        return "searchReaders";
    }

    @PostMapping("/searchReaders")
    public String processSearchReadersRequest(@ModelAttribute(value = "request") SearchReaderRequest request, ModelMap modelMap) {
        SearchReadersResponse response = searchReadersService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "searchReaders";
        } else {
            modelMap.addAttribute("readers", response.getReaders());
            return "/searchReadersResults";
        }
    }
}
