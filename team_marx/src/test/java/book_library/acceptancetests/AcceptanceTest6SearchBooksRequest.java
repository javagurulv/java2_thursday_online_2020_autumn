package book_library.acceptancetests;

import book_library.DatabaseCleaner;
import book_library.config.SpringCoreConfiguration;
import book_library.core.requests.Book.AddBookRequest;
import book_library.core.requests.Ordering;
import book_library.core.requests.Paging;
import book_library.core.requests.Book.SearchBooksRequest;
import book_library.core.responses.Book.SearchBooksResponse;
import book_library.core.services.Book.AddBookService;
import book_library.core.services.Book.SearchBooksService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest6SearchBooksRequest {

    private ApplicationContext appContext;

    @Before
    public void setup(){
        appContext = new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
        ReflectionTestUtils.setField(appContext.getBean(SearchBooksService.class),"orderingEnabled", true);
        ReflectionTestUtils.setField(appContext.getBean(SearchBooksService.class),"pagingEnabled", true);
        getDatabaseCleaner().clean();
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }

    @Test
    public void searchBooksByAuthor() {
        AddBookRequest addBookRequest1 = new AddBookRequest("Title1", "Author1");
        getAddBookService().execute(addBookRequest1);

        AddBookRequest addBookRequest2 = new AddBookRequest("Title2", "Author2");
        getAddBookService().execute(addBookRequest2);

        AddBookRequest addBookRequest3 = new AddBookRequest("Title3", "Author1");
        getAddBookService().execute(addBookRequest3);

        SearchBooksRequest searchBooksRequest = new SearchBooksRequest(null, "Author1");
        SearchBooksResponse searchBooksResponse = getSearchBooksService().execute(searchBooksRequest);

        assertEquals(2, searchBooksResponse.getBooks().size());
        assertEquals("Title1", searchBooksResponse.getBooks().get(0).getTitle());
        assertEquals("Author1", searchBooksResponse.getBooks().get(0).getAuthor());
        assertEquals("Title3", searchBooksResponse.getBooks().get(1).getTitle());
        assertEquals("Author1", searchBooksResponse.getBooks().get(1).getAuthor());
    }

    @Test
    public void searchBooksByAuthorOrderingTitleDescending() {
        AddBookRequest addBookRequest1 = new AddBookRequest("Title3", "Author1");
        getAddBookService().execute(addBookRequest1);

        AddBookRequest addBookRequest2 = new AddBookRequest("Title1", "Author1");
        getAddBookService().execute(addBookRequest2);

        AddBookRequest addBookRequest3 = new AddBookRequest("Title2", "Author1");
        getAddBookService().execute(addBookRequest3);

        Ordering ordering = new Ordering("title", "DESCENDING");
        SearchBooksRequest searchBooksRequest = new SearchBooksRequest(null, "Author1", ordering);
        SearchBooksResponse searchBooksResponse = getSearchBooksService().execute(searchBooksRequest);

        assertEquals(3, searchBooksResponse.getBooks().size());
        assertEquals("Title3", searchBooksResponse.getBooks().get(0).getTitle());
        assertEquals("Author1", searchBooksResponse.getBooks().get(0).getAuthor());
        assertEquals("Title2", searchBooksResponse.getBooks().get(1).getTitle());
        assertEquals("Author1", searchBooksResponse.getBooks().get(1).getAuthor());
        assertEquals("Title1", searchBooksResponse.getBooks().get(2).getTitle());
        assertEquals("Author1", searchBooksResponse.getBooks().get(2).getAuthor());
    }

    @Test
    public void searchBooksByAuthorOrderingTitleAscending() {
        AddBookRequest addBookRequest1 = new AddBookRequest("Title3", "Author1");
        getAddBookService().execute(addBookRequest1);

        AddBookRequest addBookRequest2 = new AddBookRequest("Title1", "Author1");
        getAddBookService().execute(addBookRequest2);

        AddBookRequest addBookRequest3 = new AddBookRequest("Title2", "Author1");
        getAddBookService().execute(addBookRequest3);

        Ordering ordering = new Ordering("title", "ASCENDING");
        SearchBooksRequest searchBooksRequest = new SearchBooksRequest(null, "Author1", ordering);
        SearchBooksResponse searchBooksResponse = getSearchBooksService().execute(searchBooksRequest);

        assertEquals(3, searchBooksResponse.getBooks().size());
        assertEquals("Title1", searchBooksResponse.getBooks().get(0).getTitle());
        assertEquals("Author1", searchBooksResponse.getBooks().get(0).getAuthor());
        assertEquals("Title2", searchBooksResponse.getBooks().get(1).getTitle());
        assertEquals("Author1", searchBooksResponse.getBooks().get(1).getAuthor());
        assertEquals("Title3", searchBooksResponse.getBooks().get(2).getTitle());
        assertEquals("Author1", searchBooksResponse.getBooks().get(2).getAuthor());
    }

    @Test
    public void searchBooksByAuthorOrderingTitleAscendingPaging() {
        AddBookRequest addBookRequest1 = new AddBookRequest("Title3", "Author1");
        getAddBookService().execute(addBookRequest1);

        AddBookRequest addBookRequest2 = new AddBookRequest("Title1", "Author1");
        getAddBookService().execute(addBookRequest2);

        AddBookRequest addBookRequest3 = new AddBookRequest("Title2", "Author1");
        getAddBookService().execute(addBookRequest3);

        Ordering ordering = new Ordering("title", "ASCENDING");
        Paging paging = new Paging(2, 2);
        SearchBooksRequest searchBooksRequest = new SearchBooksRequest(null, "Author1", ordering, paging);
        SearchBooksResponse searchBooksResponse = getSearchBooksService().execute(searchBooksRequest);

        assertEquals(1, searchBooksResponse.getBooks().size());
        assertEquals("Title3", searchBooksResponse.getBooks().get(0).getTitle());
        assertEquals("Author1", searchBooksResponse.getBooks().get(0).getAuthor());
    }


    private AddBookService getAddBookService() {
        return appContext.getBean(AddBookService.class);
    }

    private SearchBooksService getSearchBooksService() {
        return appContext.getBean(SearchBooksService.class);
    }
}
