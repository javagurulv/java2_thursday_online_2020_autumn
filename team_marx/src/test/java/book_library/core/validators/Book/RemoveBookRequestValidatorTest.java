package book_library.core.validators.Book;

import book_library.core.database.Book.BookRepository;
import book_library.core.database.ReaderBook.ReaderBookRepository;
import book_library.core.requests.Book.AddBookRequest;
import book_library.core.requests.Book.RemoveBookRequest;
import book_library.core.responses.CoreError;
import book_library.matchers.RemoveBookRequestMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class RemoveBookRequestValidatorTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ReaderBookRepository readerBookRepository;

    @InjectMocks
    private RemoveBookRequestValidator validator;

    @Test
    public void success() {
        RemoveBookRequest request = new RemoveBookRequest (1L);
        Mockito.when(bookRepository.isSuchIdPresentsInDatabase(any())).thenReturn(true);
        Mockito.when(readerBookRepository.isBookInLibrary(any())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        Mockito.verify(bookRepository).isSuchIdPresentsInDatabase(
                argThat(new RemoveBookRequestMatcher(1L)));
        Mockito.verify(readerBookRepository).isBookInLibrary(
                argThat(new RemoveBookRequestMatcher(1L)));
        assertEquals(0 , errors.size());
    }

    @Test
    public void shouldReturnErrorWhenIdIsNull() {
        RemoveBookRequest request = new RemoveBookRequest (null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1,errors.size());
        assertEquals("id", errors.get(0).getField());
        assertEquals("Must not be empty", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenIdIsNotPresentInDatabase() {
        RemoveBookRequest request = new RemoveBookRequest (1L);
        Mockito.when(bookRepository.isSuchIdPresentsInDatabase(any())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1,errors.size());
        assertEquals("id", errors.get(0).getField());
        assertEquals("No book with such id found!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenBookWithSuchIdIsToken() {
        RemoveBookRequest request = new RemoveBookRequest (1L);
        Mockito.when(bookRepository.isSuchIdPresentsInDatabase(any())).thenReturn(true);
        Mockito.when(readerBookRepository.isBookInLibrary(any())).thenReturn(true);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1,errors.size());
        assertEquals("id", errors.get(0).getField());
        assertEquals("You can't delete this, because there is an record with this book Id in ReaderBook", errors.get(0).getMessage());
    }

}