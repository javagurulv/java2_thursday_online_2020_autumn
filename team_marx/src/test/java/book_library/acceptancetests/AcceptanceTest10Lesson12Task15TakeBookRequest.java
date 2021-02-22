package book_library.acceptancetests;


import book_library.TestDatabaseRestorer;
import book_library.config.SpringCoreConfiguration;
import book_library.core.requests.Book.AddBookRequest;
import book_library.core.requests.Reader.RegisterReaderRequest;
import book_library.core.requests.ReaderBook.TakeBookRequest;
import book_library.core.responses.ReaderBook.TakeBookResponse;
import book_library.core.services.Book.AddBookService;
import book_library.core.services.Reader.RegisterReaderService;
import book_library.core.services.ReaderBooks.TakeBookService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest10Lesson12Task15TakeBookRequest {

    private ApplicationContext appContext;

    @Before
    public void setup(){

        appContext = new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
        getDatabaseRestorer().execute();
    }

    private TestDatabaseRestorer getDatabaseRestorer() {
        return appContext.getBean(TestDatabaseRestorer.class);
    }

    @Test
    public void success() throws ParseException {
        AddBookRequest addBookRequest= new AddBookRequest("Title1", "Author1");
        getAddBookService().execute(addBookRequest);

        RegisterReaderRequest registerReaderRequest = new RegisterReaderRequest("FirstName", "LastName", 11111111111L);
        getRegisterReaderService().execute(registerReaderRequest);

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date bookOutDate = formatter1.parse("2020/01/01 14:45");
        TakeBookRequest takeBookRequest = new TakeBookRequest(1L,1L, bookOutDate);
        TakeBookResponse takeBookResponse = getTakeBookService().execute(takeBookRequest);

        assertEquals(null, takeBookResponse.getErrors());
    }

    @Test
    public void shouldReturnErrorWhenBookIsNotInLibrary() throws ParseException {
        AddBookRequest addBookRequest= new AddBookRequest("Title1", "Author1");
        getAddBookService().execute(addBookRequest);

        RegisterReaderRequest registerReaderRequest = new RegisterReaderRequest("FirstName", "LastName", 11111111111L);
        getRegisterReaderService().execute(registerReaderRequest);

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date bookOutDate = formatter1.parse("2020/01/01 14:45");
        TakeBookRequest takeBookRequest1 = new TakeBookRequest(1L,1L, bookOutDate);
        getTakeBookService().execute(takeBookRequest1);
        TakeBookRequest takeBookRequest2 = new TakeBookRequest(1L,1L, bookOutDate);
        TakeBookResponse takeBookResponse = getTakeBookService().execute(takeBookRequest2);

        assertEquals(1, takeBookResponse.getErrors().size());
        assertEquals("bookId", takeBookResponse.getErrors().get(0).getField());
        assertEquals("This book is already taken from Library.", takeBookResponse.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenNoReaderWithSuchIdIsInLibrary() throws ParseException {
        AddBookRequest addBookRequest= new AddBookRequest("Title1", "Author1");
        getAddBookService().execute(addBookRequest);

        RegisterReaderRequest registerReaderRequest = new RegisterReaderRequest("FirstName", "LastName", 11111111111L);
        getRegisterReaderService().execute(registerReaderRequest);

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date bookOutDate = formatter1.parse("2020/01/01 14:45");
        TakeBookRequest takeBookRequest = new TakeBookRequest(2L,1L, bookOutDate);
        TakeBookResponse takeBookResponse = getTakeBookService().execute(takeBookRequest);

        assertEquals(1, takeBookResponse.getErrors().size());
        assertEquals("readerId", takeBookResponse.getErrors().get(0).getField());
        assertEquals("No reader with such id is present in database!", takeBookResponse.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenNoBookWithSuchIdIsInInLibrary() throws ParseException {
        AddBookRequest addBookRequest= new AddBookRequest("Title1", "Author1");
        getAddBookService().execute(addBookRequest);

        RegisterReaderRequest registerReaderRequest = new RegisterReaderRequest("FirstName", "LastName", 11111111111L);
        getRegisterReaderService().execute(registerReaderRequest);

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date bookOutDate = formatter1.parse("2020/01/01 14:45");
        TakeBookRequest takeBookRequest = new TakeBookRequest(1L,2L, bookOutDate);
        TakeBookResponse takeBookResponse = getTakeBookService().execute(takeBookRequest);

        assertEquals(1, takeBookResponse.getErrors().size());
        assertEquals("bookId", takeBookResponse.getErrors().get(0).getField());
        assertEquals("No book with such id is present in database!", takeBookResponse.getErrors().get(0).getMessage());
    }

    private AddBookService getAddBookService() {
        return appContext.getBean(AddBookService.class);
    }
    private RegisterReaderService getRegisterReaderService() {
        return appContext.getBean(RegisterReaderService.class);
    }
    private TakeBookService getTakeBookService() {
        return appContext.getBean(TakeBookService.class);
    }
}
