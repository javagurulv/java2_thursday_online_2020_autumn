package book_library.acceptancetests;

import book_library.TestDatabaseRestorer;
import book_library.config.SpringCoreConfiguration;
import book_library.core.requests.Book.AddBookRequest;
import book_library.core.requests.Reader.RegisterReaderRequest;
import book_library.core.requests.ReaderBook.ReturnBookRequest;
import book_library.core.requests.ReaderBook.TakeBookRequest;
import book_library.core.responses.ReaderBook.ReturnBookResponse;
import book_library.core.responses.ReaderBook.TakeBookResponse;
import book_library.core.services.Book.AddBookService;
import book_library.core.services.Reader.RegisterReaderService;
import book_library.core.services.ReaderBooks.ReturnBookService;
import book_library.core.services.ReaderBooks.TakeBookService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest11Lesson12Task16ReturnBookRequest {

    private ApplicationContext appContext;

    @Before
    public void setup() {

        appContext = new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
        getDatabaseRestorer().execute();
    }

    private TestDatabaseRestorer getDatabaseRestorer() {
        return appContext.getBean(TestDatabaseRestorer.class);
    }

    @Test
    public void success() throws ParseException {
        AddBookRequest addBookRequest = new AddBookRequest("Title1", "Author1");
        getAddBookService().execute(addBookRequest);

        RegisterReaderRequest registerReaderRequest = new RegisterReaderRequest("FirstName", "LastName", 11111111111L);
        getRegisterReaderService().execute(registerReaderRequest);

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date bookOutDate = formatter1.parse("2020/01/01 14:45");
        TakeBookRequest takeBookRequest = new TakeBookRequest(1L, 1L, bookOutDate);
        getTakeBookService().execute(takeBookRequest);

        Date bookReturnDate = formatter1.parse("9020/01/01 14:45");
        ReturnBookRequest returnBookRequest = new ReturnBookRequest(1L, 1L, bookReturnDate);
        ReturnBookResponse returnBookResponse = getReturnBookService().execute(returnBookRequest);

        assertEquals(null, returnBookResponse.getErrors());
    }

    @Test
    public void shouldReturnErrorWhenNoReaderWithSuchIdIsInLibrary() throws ParseException {
        AddBookRequest addBookRequest = new AddBookRequest("Title1", "Author1");
        getAddBookService().execute(addBookRequest);

        RegisterReaderRequest registerReaderRequest = new RegisterReaderRequest("FirstName", "LastName", 11111111111L);
        getRegisterReaderService().execute(registerReaderRequest);

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        Date bookReturnDate = formatter1.parse("9020/01/01 14:45");
        ReturnBookRequest returnBookRequest = new ReturnBookRequest(2L, 1L, bookReturnDate);
        ReturnBookResponse returnBookResponse = getReturnBookService().execute(returnBookRequest);

        assertEquals(1, returnBookResponse.getErrors().size());
        assertEquals("readerId", returnBookResponse.getErrors().get(0).getField());
        assertEquals("No reader with such id is present in database!", returnBookResponse.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenNoBookWithSuchIdIsInLibrary() throws ParseException {
        AddBookRequest addBookRequest = new AddBookRequest("Title1", "Author1");
        getAddBookService().execute(addBookRequest);

        RegisterReaderRequest registerReaderRequest = new RegisterReaderRequest("FirstName", "LastName", 11111111111L);
        getRegisterReaderService().execute(registerReaderRequest);

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        Date bookReturnDate = formatter1.parse("9020/01/01 14:45");
        ReturnBookRequest returnBookRequest = new ReturnBookRequest(1L, 2L, bookReturnDate);
        ReturnBookResponse returnBookResponse = getReturnBookService().execute(returnBookRequest);

        assertEquals(1, returnBookResponse.getErrors().size());
        assertEquals("bookId", returnBookResponse.getErrors().get(0).getField());
        assertEquals("No book with such id is present in database!", returnBookResponse.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenBookIsInLibrary() throws ParseException {
        AddBookRequest addBookRequest = new AddBookRequest("Title1", "Author1");
        getAddBookService().execute(addBookRequest);

        RegisterReaderRequest registerReaderRequest = new RegisterReaderRequest("FirstName", "LastName", 11111111111L);
        getRegisterReaderService().execute(registerReaderRequest);

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        Date bookReturnDate = formatter1.parse("9020/01/01 14:45");
        ReturnBookRequest returnBookRequest = new ReturnBookRequest(1L, 1L, bookReturnDate);
        ReturnBookResponse returnBookResponse = getReturnBookService().execute(returnBookRequest);

        assertEquals(1, returnBookResponse.getErrors().size());
        assertEquals("bookId", returnBookResponse.getErrors().get(0).getField());
        assertEquals("This book is already in Library.", returnBookResponse.getErrors().get(0).getMessage());
    }

    @Test
    public void successByTakingBookAgain() throws ParseException {
        AddBookRequest addBookRequest = new AddBookRequest("Title1", "Author1");
        getAddBookService().execute(addBookRequest);

        RegisterReaderRequest registerReaderRequest = new RegisterReaderRequest("FirstName", "LastName", 11111111111L);
        getRegisterReaderService().execute(registerReaderRequest);

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date bookOutDate = formatter1.parse("2020/01/01 14:45");
        TakeBookRequest takeBookRequest = new TakeBookRequest(1L, 1L, bookOutDate);
        getTakeBookService().execute(takeBookRequest);

        Date bookReturnDate = formatter1.parse("9020/01/01 14:45");
        ReturnBookRequest returnBookRequest = new ReturnBookRequest(1L, 1L, bookReturnDate);
        getReturnBookService().execute(returnBookRequest);

        Date bookOutDate2 = formatter1.parse("2020/01/01 14:45");
        TakeBookRequest takeBookRequest2 = new TakeBookRequest(1L, 1L, bookOutDate);
        TakeBookResponse takeBookResponse = getTakeBookService().execute(takeBookRequest2);

        assertEquals(null, takeBookResponse.getErrors());
    }

    private AddBookService getAddBookService() {
        return appContext.getBean(AddBookService.class);
    }

    private RegisterReaderService getRegisterReaderService() {
        return appContext.getBean(RegisterReaderService.class);
    }

    private ReturnBookService getReturnBookService() {
        return appContext.getBean(ReturnBookService.class);
    }

    private TakeBookService getTakeBookService() {
        return appContext.getBean(TakeBookService.class);
    }
}
