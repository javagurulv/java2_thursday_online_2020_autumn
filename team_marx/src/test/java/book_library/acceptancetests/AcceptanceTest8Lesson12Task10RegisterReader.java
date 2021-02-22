package book_library.acceptancetests;

import book_library.DatabaseCleaner;
import book_library.config.SpringCoreConfiguration;
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

        appContext = new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
        getDatabaseCleaner().clean();
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }

    @Test
    public void shouldPasseValidation() {
        RegisterReaderRequest request = new RegisterReaderRequest("FirstName", "LastName", 11111111111L);
        RegisterReaderResponse response = getRegisterReaderService().execute(request);

        assertEquals(null, response.getErrors());
    }

    @Test
    public void shouldReturnErrorNotValidFirstName() {
        RegisterReaderRequest request = new RegisterReaderRequest(null,"LastName", 11111111111L);
        RegisterReaderResponse response = getRegisterReaderService().execute(request);

        assertEquals(1, response.getErrors().size());
        assertEquals("firstName", response.getErrors().get(0).getField());
        assertEquals("Must not be empty!", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorNotValidLastName() {
        RegisterReaderRequest request = new RegisterReaderRequest("FirstName",null, 11111111111L);
        RegisterReaderResponse response = getRegisterReaderService().execute(request);

        assertEquals(1, response.getErrors().size());
        assertEquals("lastName", response.getErrors().get(0).getField());
        assertEquals("Must not be empty!", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorSuchReaderAlreadyIsRegistered() {
        RegisterReaderRequest request1 = new RegisterReaderRequest("FirstName","LastName", 11111111111L);
        getRegisterReaderService().execute(request1);

        RegisterReaderRequest request2 = new RegisterReaderRequest("FirstName","LastName", 11111111111L);
        RegisterReaderResponse response = getRegisterReaderService().execute(request2);

        assertEquals(1, response.getErrors().size());
        assertEquals("First name, last name and personal code", response.getErrors().get(0).getField());
        assertEquals("This reader already is registered", response.getErrors().get(0).getMessage());
    }

    private RegisterReaderService getRegisterReaderService() {
        return appContext.getBean(RegisterReaderService.class);
    }
}
