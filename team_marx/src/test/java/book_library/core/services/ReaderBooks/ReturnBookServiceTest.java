package book_library.core.services.ReaderBooks;

import book_library.core.database.ReaderBook.ReaderBookRepository;
import book_library.core.requests.ReaderBook.ReturnBookRequest;
import book_library.core.responses.CoreError;
import book_library.core.responses.ReaderBook.ReturnBookResponse;
import book_library.core.validators.ReaderBook.ReturnBookValidator;
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

@RunWith(MockitoJUnitRunner.class)
public class ReturnBookServiceTest {

    @Mock
    private ReaderBookRepository readerBookRepository;

    @Mock
    private ReturnBookValidator validator;

    @InjectMocks
    private ReturnBookService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date bookReturnDate = formatter1.parse("2020/01/01 14:45");
        ReturnBookRequest request = new ReturnBookRequest(null, 1L, bookReturnDate);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("readerId", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        ReturnBookResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("readerId", response.getErrors().get(0).getField());
        assertEquals("Must not be empty!", response.getErrors().get(0).getMessage());

        Mockito.verifyNoInteractions(readerBookRepository);
    }

    @Test
    public void shouldRegisterBookReturning() throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date bookReturnDate = formatter1.parse("9020/01/01 14:45");
        ReturnBookRequest request = new ReturnBookRequest(1L, 1L, bookReturnDate);

        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());

        ReturnBookResponse response = service.execute(request);

        assertFalse(response.hasErrors());
        Mockito.verify(readerBookRepository).returnBook(request);
    }
}