package electronic_library.acceptance_tests;

import electronic_library.DatabaseCleaner;
import electronic_library.config.BookListConfiguration;
import electronic_library.core.requests.AddBookRequest;
import electronic_library.core.requests.DeleteBookByIdRequest;
import electronic_library.core.requests.GetAllBooksRequest;
import electronic_library.core.responses.DeleteBookByIdResponse;
import electronic_library.core.responses.GetAllBooksResponse;
import electronic_library.core.services.AddBookService;
import electronic_library.core.services.DeleteBookByIdService;
import electronic_library.core.services.GetAllBooksService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@Profile("orm")
public class AcceptanceTest_DeleteBookById {

    private AnnotationConfigApplicationContext applicationContext;

    @Before
    public void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(BookListConfiguration.class);
        getDatabaseCleaner().clean();
        AddBookRequest addBookRequestOne = new AddBookRequest("aaa", "aaa", new BigDecimal("10.00"), 2010);
        getAddBookService().execute(addBookRequestOne);
        AddBookRequest addBookRequestTwo = new AddBookRequest("bbb", "bbb", new BigDecimal("11.00"), 2011);
        getAddBookService().execute(addBookRequestTwo);
    }

    @Test
    public void deleteByBookIdWithoutErrors() {

        DeleteBookByIdRequest deleteRequest = new DeleteBookByIdRequest(2L);
        DeleteBookByIdResponse deleteResponse = getDeleteBookByIdService().deleteBookByIdResponse(deleteRequest);

        GetAllBooksRequest request = new GetAllBooksRequest();
        GetAllBooksResponse response = getAllBooksService().execute(request);
        assertEquals(1, response.getBooks().size());

        assertTrue(deleteResponse.isBookRemoved());
        assertFalse(deleteResponse.hasErrors());
    }

    @Test
    public void deleteByBookIdWhenNotBookWithThisId() {
        DeleteBookByIdRequest deleteRequest = new DeleteBookByIdRequest(3L);
        DeleteBookByIdResponse deleteResponse = getDeleteBookByIdService().deleteBookByIdResponse(deleteRequest);

        GetAllBooksRequest request = new GetAllBooksRequest();
        GetAllBooksResponse response = getAllBooksService().execute(request);
        assertEquals(2, response.getBooks().size());
        assertFalse(deleteResponse.isBookRemoved());

    }
    private AddBookService getAddBookService() { return applicationContext.getBean(AddBookService.class); }
    private GetAllBooksService getAllBooksService() { return applicationContext.getBean(GetAllBooksService.class); }
    private DeleteBookByIdService getDeleteBookByIdService() { return applicationContext.getBean(DeleteBookByIdService.class); }
    private DatabaseCleaner getDatabaseCleaner() {
        return applicationContext.getBean(DatabaseCleaner.class);
    }

}
