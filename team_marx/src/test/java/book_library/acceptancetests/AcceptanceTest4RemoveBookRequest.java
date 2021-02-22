package book_library.acceptancetests;

import book_library.TestDatabaseRestorer;
import book_library.config.SpringCoreConfiguration;
import book_library.core.requests.Book.AddBookRequest;
import book_library.core.requests.Book.GetAllBooksRequest;
import book_library.core.requests.Book.RemoveBookRequest;
import book_library.core.responses.Book.GetAllBooksResponse;
import book_library.core.responses.Book.RemoveBookResponse;
import book_library.core.services.Book.AddBookService;
import book_library.core.services.Book.GetAllBooksService;
import book_library.core.services.Book.RemoveBookService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest4RemoveBookRequest {

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
    public void shouldRemoveBook() {
        AddBookRequest addBookRequest1 = new AddBookRequest("Title1", "Author1");
        getAddBookService().execute(addBookRequest1);
        AddBookRequest addBookRequest2 = new AddBookRequest("Title2", "Author2");
        getAddBookService().execute(addBookRequest2);

        GetAllBooksRequest getAllBooksRequest1 = new GetAllBooksRequest();
        GetAllBooksResponse getAllBooksResponse1 = getAllBooksService().execute(getAllBooksRequest1);
        assertEquals(2, getAllBooksResponse1.getBooks().size());
        assertEquals("Title1", getAllBooksResponse1.getBooks().get(0).getTitle());
        assertEquals("Author1", getAllBooksResponse1.getBooks().get(0).getAuthor());
        assertEquals(java.util.Optional.of(1L), java.util.Optional.of(getAllBooksResponse1.getBooks().get(0).getId()));
        assertEquals("Title2", getAllBooksResponse1.getBooks().get(1).getTitle());
        assertEquals("Author2", getAllBooksResponse1.getBooks().get(1).getAuthor());
        assertEquals(java.util.Optional.of(2L), java.util.Optional.of(getAllBooksResponse1.getBooks().get(1).getId()));


        RemoveBookRequest removeBookRequest1 = new RemoveBookRequest(1L);
        RemoveBookResponse removeBookResponse1 = getRemoveBookService().execute(removeBookRequest1);
        assertEquals(true, removeBookResponse1.isBookRemoved());

        GetAllBooksResponse getAllBooksResponse2 = getAllBooksService().execute(getAllBooksRequest1);
        assertEquals(1, getAllBooksResponse2.getBooks().size());
        assertEquals("Title2", getAllBooksResponse2.getBooks().get(0).getTitle());
        assertEquals("Author2", getAllBooksResponse2.getBooks().get(0).getAuthor());
        assertEquals(java.util.Optional.of(2L), java.util.Optional.of(getAllBooksResponse2.getBooks().get(0).getId()));

    }

    @Test
    public void shouldReturnErrorNotValidId() {

        RemoveBookRequest removeBookRequest1 = new RemoveBookRequest(null);
        RemoveBookResponse removeBookResponse1 = getRemoveBookService().execute(removeBookRequest1);
        assertEquals(1, removeBookResponse1.getErrors().size());
        assertEquals("id", removeBookResponse1.getErrors().get(0).getField());
        assertEquals("Must not be empty", removeBookResponse1.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorNoBookWithSuchIdFound() {
        RemoveBookRequest removeBookRequest1 = new RemoveBookRequest(1L);
        RemoveBookResponse removeBookResponse1 = getRemoveBookService().execute(removeBookRequest1);
        assertEquals(1, removeBookResponse1.getErrors().size());
        assertEquals("id", removeBookResponse1.getErrors().get(0).getField());
        assertEquals("No book with such id found!", removeBookResponse1.getErrors().get(0).getMessage());
    }

    private AddBookService getAddBookService() {

        return appContext.getBean(AddBookService.class);
    }

    private GetAllBooksService getAllBooksService() {

        return appContext.getBean(GetAllBooksService.class);
    }

    private RemoveBookService getRemoveBookService() {

        return appContext.getBean(RemoveBookService.class);
    }
}
