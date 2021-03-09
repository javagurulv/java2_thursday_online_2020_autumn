package book_library.web_ui.controllers;

import book_library.core.requests.ReaderBook.TakeBookRequest;
import book_library.core.responses.ReaderBook.TakeBookResponse;
import book_library.core.services.ReaderBooks.TakeBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TakeBookController {

    @Autowired
    private TakeBookService takeBookService;

    @GetMapping(value = "/takeBook")
    public String takeBookPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new TakeBookRequest());
        return "takeBook";
    }

    @PostMapping("/takeBook")
    public String takeBookRequest(@ModelAttribute(value = "request") TakeBookRequest request, ModelMap modelMap) {
        TakeBookResponse response = takeBookService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "takeBook";
        } else {
            return "redirect:/";
        }
    }

}
