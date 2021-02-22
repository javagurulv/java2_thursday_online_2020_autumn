package book_library.acceptancetests;

import book_library.DatabaseCleaner;
import book_library.config.SpringCoreConfiguration;
import book_library.core.requests.Book.AddBookRequest;
import book_library.core.responses.Book.AddBookResponse;
import book_library.core.services.Book.AddBookService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest2AddBookRequestValidation {

    private ApplicationContext appContext;

    @Before
    public void setup(){

        appContext = new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
        getDatabaseCleaner().clean();
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }

    @Test
    public void shouldPasseValidation() {
        AddBookRequest addBookRequest1 = new AddBookRequest("Title", "Author");
        AddBookResponse response = getAddBookService().execute(addBookRequest1);

        assertEquals(null, response.getErrors());
    }

    @Test
    public void shouldReturnErrorNotValidTitle() {
        AddBookRequest addBookRequest1 = new AddBookRequest(null, "Author");
        AddBookResponse response = getAddBookService().execute(addBookRequest1);

        assertEquals(1, response.getErrors().size());
        assertEquals("title", response.getErrors().get(0).getField());
        assertEquals("Must not be empty", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorNotValidAuthor() {
        AddBookRequest addBookRequest1 = new AddBookRequest("Title", null);
        AddBookResponse response = getAddBookService().execute(addBookRequest1);

        assertEquals(1, response.getErrors().size());
        assertEquals("author", response.getErrors().get(0).getField());
        assertEquals("Must not be empty", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorSuchBookInDatabase() {
        AddBookRequest addBookRequest1 = new AddBookRequest("Title", "Author");
        getAddBookService().execute(addBookRequest1);

        AddBookRequest addBookRequest2 = new AddBookRequest("Title", "Author");
        AddBookResponse response = getAddBookService().execute(addBookRequest2);

        assertEquals(1, response.getErrors().size());
        assertEquals("Title and author", response.getErrors().get(0).getField());
        assertEquals("Such book already exists!", response.getErrors().get(0).getMessage());
    }

    private AddBookService getAddBookService() {
        return appContext.getBean(AddBookService.class);
    }
}
