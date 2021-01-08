package book_library.acceptancetests;

import book_library.dependency_injection.ApplicationContext;
import book_library.core.requests.*;
import book_library.core.responses.SearchBooksResponse;
import book_library.core.services.AddBookService;
import book_library.core.services.RemoveBookService;
import book_library.core.services.SearchBooksService;
import book_library.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest7CustomTest {
    private ApplicationContext appContext = new DIApplicationContextBuilder().build("book_library");

    @Test
    public void searchBooksByAuthorOrderingTitleAscendingPaging() {
        AddBookRequest addBookRequest1 = new AddBookRequest("Title3", "Author1");
        getAddBookService().execute(addBookRequest1);

        AddBookRequest addBookRequest2 = new AddBookRequest("Title1", "Author1");
        getAddBookService().execute(addBookRequest2);

        AddBookRequest addBookRequest3 = new AddBookRequest("Title2", "Author1");
        getAddBookService().execute(addBookRequest3);

        RemoveBookRequest removeBookRequest1 = new RemoveBookRequest(2L);
        getRemoveBookService().execute(removeBookRequest1);

        AddBookRequest addBookRequest4 = new AddBookRequest(null, "Author1");
        getAddBookService().execute(addBookRequest4);

        AddBookRequest addBookRequest5 = new AddBookRequest("Title6", null);
        getAddBookService().execute(addBookRequest5);

        AddBookRequest addBookRequest6 = new AddBookRequest("Title5", "Author1");
        getAddBookService().execute(addBookRequest6);

        AddBookRequest addBookRequest7 = new AddBookRequest("Title7", "Author1");
        getAddBookService().execute(addBookRequest7);

        AddBookRequest addBookRequest8 = new AddBookRequest("Title1", "Author2");
        getAddBookService().execute(addBookRequest8);

        AddBookRequest addBookRequest9 = new AddBookRequest("Title2", "Author2");
        getAddBookService().execute(addBookRequest9);

        RemoveBookRequest removeBookRequest2 = new RemoveBookRequest(4L);
        getRemoveBookService().execute(removeBookRequest2);

        Ordering ordering = new Ordering("title", "ASCENDING");
        Paging paging = new Paging(2, 2);
        SearchBooksRequest searchBooksRequest = new SearchBooksRequest(null, "Author1", ordering, paging);
        SearchBooksResponse searchBooksResponse = getSearchBooksService().execute(searchBooksRequest);

        assertEquals(1, searchBooksResponse.getBooks().size());
        assertEquals("Title7", searchBooksResponse.getBooks().get(0).getTitle());
        assertEquals("Author1", searchBooksResponse.getBooks().get(0).getAuthor());
    }


    private AddBookService getAddBookService() {
        return appContext.getBean(AddBookService.class);
    }

    private SearchBooksService getSearchBooksService() {
        return appContext.getBean(SearchBooksService.class);
    }

    private RemoveBookService getRemoveBookService() {

        return appContext.getBean(RemoveBookService.class);
    }
}
