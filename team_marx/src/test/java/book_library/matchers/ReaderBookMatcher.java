package book_library.matchers;

import book_library.core.domain.Book;
import book_library.core.domain.Reader;
import book_library.core.domain.ReaderBook;
import org.mockito.ArgumentMatcher;

import java.util.Date;

public class ReaderBookMatcher implements ArgumentMatcher<ReaderBook> {

    private Reader reader;
    private Book book;
    private Date bookOutData;


    public ReaderBookMatcher(Reader reader, Book book, Date bookOutData) {
        this.reader = reader;
        this.book = book;
        this.bookOutData = bookOutData;
    }

    @Override
    public boolean matches(ReaderBook readerBook) {
        return readerBook.getReader().equals(reader)
                && readerBook.getBook().equals(book)
                && readerBook.getBookOutData().equals(bookOutData);
    }

}
