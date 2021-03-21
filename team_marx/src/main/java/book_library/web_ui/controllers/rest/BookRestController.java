package book_library.web_ui.controllers.rest;

import book_library.core.requests.Book.AddBookRequest;
import book_library.core.requests.Book.DeleteBookRequest;
import book_library.core.requests.Book.GetBookRequest;
import book_library.core.requests.Book.UpdateBookRequest;
import book_library.core.responses.Book.AddBookResponse;
import book_library.core.responses.Book.DeleteBookResponse;
import book_library.core.responses.Book.GetBookResponse;
import book_library.core.responses.Book.UpdateBookResponse;
import book_library.core.services.Book.AddBookService;
import book_library.core.services.Book.DeleteBookService;
import book_library.core.services.Book.GetBookService;
import book_library.core.services.Book.UpdateBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookRestController {

    @Autowired private GetBookService getBookService;
    @Autowired private AddBookService addBookService;
    @Autowired private UpdateBookService updateBookService;
    @Autowired private DeleteBookService deleteBookService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GetBookResponse getBook(@PathVariable Long id) {
        GetBookRequest request = new GetBookRequest(id);
        return getBookService.execute(request);
    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddBookResponse addBook(@RequestBody AddBookRequest request) {
        return addBookService.execute(request);
    }

    @PutMapping(path = "/{id}",
            consumes = "application/json",
            produces = "application/json")
    public UpdateBookResponse updateBook (@RequestBody UpdateBookRequest request) {
        return updateBookService.execute(request);
    }

    @DeleteMapping (path = "/{id}", produces = "application/json")
    public DeleteBookResponse deleteBook (@PathVariable Long id) {
        DeleteBookRequest request = new DeleteBookRequest(id);
        return deleteBookService.execute(request);
    }


}
