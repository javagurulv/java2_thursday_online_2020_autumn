package book_library.web_ui.controllers.rest;

import book_library.core.requests.Book.GetBookRequest;
import book_library.core.responses.Book.GetBookResponse;
import book_library.core.services.Book.GetBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookRestController {

    @Autowired private GetBookService getBookService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GetBookResponse getBook(@PathVariable Long id) {
        GetBookRequest request = new GetBookRequest(id);
        return getBookService.execute(request);
    }

}
