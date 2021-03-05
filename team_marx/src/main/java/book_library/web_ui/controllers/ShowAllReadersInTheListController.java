package book_library.web_ui.controllers;

import book_library.core.requests.Reader.GetAllReaderRequest;
import book_library.core.responses.Reader.GetAllReadersResponse;
import book_library.core.services.Reader.GetAllReadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllReadersInTheListController {

    @Autowired
    private GetAllReadersService getAllReadersService;


    @GetMapping(value = "/showAllReadersInTheList")
    public String showAllReaders(ModelMap modelMap) {
        GetAllReadersResponse response = getAllReadersService.execute(
                new GetAllReaderRequest()
        );
        modelMap.addAttribute("readers", response.getReaders());
        return "/showAllReadersInTheList";
    }
}
