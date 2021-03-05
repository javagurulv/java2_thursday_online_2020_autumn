package book_library.web_ui.controllers;

import book_library.core.requests.Book.AddBookRequest;
import book_library.core.requests.Reader.RegisterReaderRequest;
import book_library.core.responses.Book.AddBookResponse;
import book_library.core.responses.Reader.RegisterReaderResponse;
import book_library.core.services.Reader.RegisterReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterReaderController {

    @Autowired
    private RegisterReaderService registerReaderService;

    @GetMapping(value = "/registerReader")
    public String showRegisterReaderPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new RegisterReaderRequest());
        return "registerReader";
    }

    @PostMapping("/registerReader")
    public String processRegisterReaderRequest(@ModelAttribute(value = "request") RegisterReaderRequest request, ModelMap modelMap) {
        RegisterReaderResponse response = registerReaderService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "registerReader";
        } else {
            return "redirect:/";
        }
    }
}
