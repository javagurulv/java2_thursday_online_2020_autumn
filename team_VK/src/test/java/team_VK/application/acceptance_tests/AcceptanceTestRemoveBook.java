package team_VK.application.acceptance_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import team_VK.application.core.requests.RemoveBookRequest;
import team_VK.application.core.responses.RemoveBookResponse;
import team_VK.application.core.services.main_menu_services.RemoveBookService;
import team_VK.application.database.BookRepository;

public class AcceptanceTestRemoveBook {

    @Autowired
    private BookRepository database;
    @Autowired
    private RemoveBookService removeBookService;

    @Before
    public void setup() {    }

    @Test
    public void shouldRemoveCorrectBook(){

        int bookNumberBeforeTest = database.getListBooks().size();

        RemoveBookRequest removeBookRequest = new RemoveBookRequest(6, "Good bay, weapon");
        RemoveBookResponse removeBookResponse = removeBookService.removeBook(removeBookRequest);


        Assert.assertFalse(removeBookResponse.havesError());
        Assert.assertEquals(removeBookResponse.errorList.size(), 0);
        Assert.assertEquals(database.getListBooks().size(), bookNumberBeforeTest-1);
        System.out.println();
    }

    @Test
    public void shouldNotRemoveInCorrectBook(){

        int bookNumberBeforeTest = database.getListBooks().size();

        RemoveBookRequest removeBookRequest = new RemoveBookRequest(7, "Good bay, weapon");
        RemoveBookResponse removeBookResponse = removeBookService.removeBook(removeBookRequest);


        Assert.assertTrue(removeBookResponse.havesError());
        Assert.assertEquals(removeBookResponse.errorList.size(), 1);
        Assert.assertEquals(removeBookResponse.getErrorList().get(0).getErrorMessage(), "ID not consist to Book Title");
        Assert.assertEquals(database.getListBooks().size(), bookNumberBeforeTest);
        System.out.println();
    }

}
