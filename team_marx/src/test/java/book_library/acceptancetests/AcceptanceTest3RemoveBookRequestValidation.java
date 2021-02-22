package book_library.acceptancetests;

import book_library.TestDatabaseRestorer;
import book_library.config.SpringCoreConfiguration;
import book_library.core.requests.Book.AddBookRequest;
import book_library.core.requests.Book.GetAllBooksRequest;
import book_library.core.requests.Book.RemoveBookRequest;
import book_library.core.responses.CoreError;
import book_library.core.responses.Book.GetAllBooksResponse;
import book_library.core.services.Book.AddBookService;
import book_library.core.services.Book.GetAllBooksService;
import book_library.core.validators.Book.RemoveBookRequestValidator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest3RemoveBookRequestValidation {

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

    private AddBookService getAddBookService() {

        return appContext.getBean(AddBookService.class);
    }

    private GetAllBooksService getAllBooksService() {

        return appContext.getBean(GetAllBooksService.class);
    }

    private RemoveBookRequestValidator getRemoveBookRequestValidator() {
        return appContext.getBean(RemoveBookRequestValidator.class);
    }
}
