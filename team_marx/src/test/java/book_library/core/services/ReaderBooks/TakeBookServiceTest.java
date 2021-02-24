package book_library.core.services.ReaderBooks;

import book_library.core.database.Book.BookRepository;
import book_library.core.database.Reader.ReaderRepository;
import book_library.core.database.ReaderBook.ReaderBookRepository;
import book_library.core.domain.Book;
import book_library.core.domain.Reader;
import book_library.core.requests.ReaderBook.TakeBookRequest;
import book_library.core.responses.CoreError;
import book_library.core.responses.ReaderBook.TakeBookResponse;
import book_library.core.validators.ReaderBook.TakeBookValidator;
import book_library.matchers.ReaderBookMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class TakeBookServiceTest {

    @Mock
    private ReaderBookRepository readerBookRepository;

    @Mock
    private ReaderRepository readerRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private TakeBookValidator validator;

    @InjectMocks
    private TakeBookService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date bookOutDate = formatter1.parse("2020/01/01 14:45");
        TakeBookRequest request = new TakeBookRequest(null, 1L, bookOutDate);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("readerId", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        TakeBookResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("readerId", response.getErrors().get(0).getField());
        assertEquals("Must not be empty!", response.getErrors().get(0).getMessage());

        Mockito.verifyNoInteractions(readerBookRepository);
        Mockito.verifyNoInteractions(readerRepository);
        Mockito.verifyNoInteractions(bookRepository);
    }

    @Test
    public void shouldRegisterBookTaking() throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date bookOutDate = formatter1.parse("2020/01/01 14:45");
        TakeBookRequest request = new TakeBookRequest(1L, 1L, bookOutDate);

        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(readerRepository.getReaderById(any())).thenReturn(new Reader("FirstName", "LastName", 11111111111L));
        Mockito.when(bookRepository.getBookById(any())).thenReturn(new Book("Title", "Author"));

        TakeBookResponse response = service.execute(request);

        assertFalse(response.hasErrors());
        Mockito.verify(readerBookRepository).save(argThat(new ReaderBookMatcher(new Reader("FirstName", "LastName", 11111111111L),
                new Book("Title", "Author"), bookOutDate)));
    }
}