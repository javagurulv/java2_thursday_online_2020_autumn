package book_library.acceptancetests;

import book_library.DatabaseCleaner;
import book_library.config.BookListConfiguration;
import book_library.core.requests.Reader.RegisterReaderRequest;
import book_library.core.responses.Reader.RegisterReaderResponse;
import book_library.core.services.Reader.RegisterReaderService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest8Lesson12Task10RegisterReader {

    private ApplicationContext appContext;

    @Before
    public void setup() {

        appContext = new AnnotationConfigApplicationContext(BookListConfiguration.class);
        getDatabaseCleaner().clean();
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }

    @Test
    public void shouldPasseValidation() {
        RegisterReaderRequest request = new RegisterReaderRequest("FirstName", "LastName");
        RegisterReaderResponse response = getRegisterReaderService().execute(request);

        assertEquals(null, response.getErrors());
    }

    @Test
    public void shouldReturnErrorNotValidFirstName() {
        RegisterReaderRequest request = new RegisterReaderRequest(null,"LastName");
        RegisterReaderResponse response = getRegisterReaderService().execute(request);

        assertEquals(1, response.getErrors().size());
        assertEquals("firstName", response.getErrors().get(0).getField());
        assertEquals("Must not be empty!", response.getErrors().get(0).getMessage());
    }

    private RegisterReaderService getRegisterReaderService() {
        return appContext.getBean(RegisterReaderService.class);
    }
}
