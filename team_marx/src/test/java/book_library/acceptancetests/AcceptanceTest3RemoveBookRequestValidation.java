package book_library.acceptancetests;

import book_library.TestDatabaseRestorer;
import book_library.config.SpringCoreConfiguration;
import book_library.console_ui.ExitUIAction;
import book_library.core.requests.Book.AddBookRequest;
import book_library.core.requests.Book.GetAllBooksRequest;
import book_library.core.requests.Book.RemoveBookRequest;
import book_library.core.requests.Reader.RegisterReaderRequest;
import book_library.core.requests.ReaderBook.TakeBookRequest;
import book_library.core.responses.CoreError;
import book_library.core.responses.Book.GetAllBooksResponse;
import book_library.core.services.Book.AddBookService;
import book_library.core.services.Book.GetAllBooksService;
import book_library.core.services.Reader.RegisterReaderService;
import book_library.core.services.ReaderBooks.TakeBookService;
import book_library.core.validators.Book.RemoveBookRequestValidator;
import book_library.web_ui.config.SpringWebConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringCoreConfiguration.class)
@WebAppConfiguration
public class AcceptanceTest3RemoveBookRequestValidation {

    private ApplicationContext appContext;

    @Before
    public void setup(){

        appContext = SpringApplication.run(SpringWebConfiguration.class);
        getDatabaseRestorer().execute();
    }

    private TestDatabaseRestorer getDatabaseRestorer() {
        return appContext.getBean(TestDatabaseRestorer.class);
    }

    @Test
    public void shouldPasseValidation() {

        AddBookRequest addBookRequest1 = new AddBookRequest("Title", "Author");
        getAddBookService().execute(addBookRequest1);

        GetAllBooksRequest getAllBooksRequest1 = new GetAllBooksRequest();
        GetAllBooksResponse response1 = getAllBooksService().execute(getAllBooksRequest1);
        assertEquals(1, response1.getBooks().size());
        assertEquals(java.util.Optional.of(1L), java.util.Optional.of(response1.getBooks().get(0).getId()));

        RemoveBookRequest removeBookRequest = new RemoveBookRequest(1L);
        List<CoreError> response2 = getRemoveBookRequestValidator().validate(removeBookRequest);
        assertEquals(new ArrayList<>(), response2);

    }

    @Test
    public void shouldReturnErrorNotValidId() {
        RemoveBookRequest removeBookRequest = new RemoveBookRequest(null);
        List<CoreError> response = getRemoveBookRequestValidator().validate(removeBookRequest);
        assertEquals(1, response.size());
        assertEquals("id", response.get(0).getField());
        assertEquals("Must not be empty", response.get(0).getMessage());

    }

    @Test
    public void shouldReturnErrorNoBookWithSuchIdFound() {
        RemoveBookRequest removeBookRequest = new RemoveBookRequest(1L);
        List<CoreError> response = getRemoveBookRequestValidator().validate(removeBookRequest);
        assertEquals(1, response.size());
        assertEquals("id", response.get(0).getField());
        assertEquals("No book with such id found!", response.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorBookIsReaderBookDatabase() throws ParseException {
        AddBookRequest addBookRequest1 = new AddBookRequest("Title", "Author");
        getAddBookService().execute(addBookRequest1);

        RegisterReaderRequest registerReaderRequest = new RegisterReaderRequest("FirstName", "LastName", 11111111111L);
        getRegisterReaderService().execute(registerReaderRequest);

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date bookOutDate = formatter1.parse("2020/01/01 14:45");
        TakeBookRequest takeBookRequest = new TakeBookRequest(1L, 1L,bookOutDate);
        getTakeBookService().execute(takeBookRequest);

        RemoveBookRequest removeBookRequest = new RemoveBookRequest(1L);
        List<CoreError> response = getRemoveBookRequestValidator().validate(removeBookRequest);
        assertEquals(1, response.size());
        assertEquals("id", response.get(0).getField());
        assertEquals("You can't delete this, because there is an record with this book Id in ReaderBook", response.get(0).getMessage());
    }

    private AddBookService getAddBookService() {

        return appContext.getBean(AddBookService.class);
    }

    private GetAllBooksService getAllBooksService() {

        return appContext.getBean(GetAllBooksService.class);
    }

    private RemoveBookRequestValidator getRemoveBookRequestValidator() {
        return appContext.getBean(RemoveBookRequestValidator.class);
    }

    private RegisterReaderService getRegisterReaderService() {

        return appContext.getBean(RegisterReaderService.class);
    }

    private TakeBookService getTakeBookService() {

        return appContext.getBean(TakeBookService.class);
    }
}
