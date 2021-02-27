package book_library.matchers;

import book_library.core.domain.Book;
import book_library.core.requests.Book.RemoveBookRequest;
import org.mockito.ArgumentMatcher;

public class RemoveBookRequestMatcher implements ArgumentMatcher<Long> {

    private Long bookIdToRemove;

    public RemoveBookRequestMatcher(Long bookIdToRemove) {
        this.bookIdToRemove = bookIdToRemove;
    }

    @Override
    public boolean matches(Long bookId) {
        return bookId.equals(bookIdToRemove);
    }
}
