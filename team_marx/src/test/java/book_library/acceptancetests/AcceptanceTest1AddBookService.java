package book_library.acceptancetests;

import book_library.DatabaseCleaner;
import book_library.config.SpringCoreConfiguration;
import book_library.core.requests.Book.AddBookRequest;
import book_library.core.requests.Book.GetAllBooksRequest;
import book_library.core.responses.Book.GetAllBooksResponse;
import book_library.core.services.Book.AddBookService;
import book_library.core.services.Book.GetAllBooksService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest1AddBookService {

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
    public void shouldReturnCorrectBookList() {
        AddBookRequest addBookRequest1 = new AddBookRequest("TitleA", "AuthorA");
        getAddBookService().execute(addBookRequest1);

        AddBookRequest addBookRequest2 = new AddBookRequest("TitleB", "AuthorA");
        getAddBookService().execute(addBookRequest2);

        GetAllBooksRequest getAllBooksRequest1 = new GetAllBooksRequest();
        GetAllBooksResponse response = getAllBooksService().execute(getAllBooksRequest1);
        assertEquals(2, response.getBooks().size());
        assertEquals("TitleA", response.getBooks().get(0).getTitle());
        assertEquals("AuthorA", response.getBooks().get(0).getAuthor());
        assertEquals("TitleB", response.getBooks().get(1).getTitle());
        assertEquals("AuthorA", response.getBooks().get(1).getAuthor());
    }

    private AddBookService getAddBookService() {
        return appContext.getBean(AddBookService.class);
    }

    private GetAllBooksService getAllBooksService() {
        return appContext.getBean(GetAllBooksService.class);
    }
}
