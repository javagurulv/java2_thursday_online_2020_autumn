package lesson_4.core.responses;

import lesson_4.core.domain.Book;

import java.util.List;

public class FindBooksResponse extends CoreResponse {

    private List<Book> books;

    public FindBooksResponse(List<Book> books, List<CoreError> errors) {
        super(errors);
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

}
