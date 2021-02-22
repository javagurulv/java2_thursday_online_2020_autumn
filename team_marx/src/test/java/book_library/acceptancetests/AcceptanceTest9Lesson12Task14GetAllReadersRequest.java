package book_library.acceptancetests;

import book_library.DatabaseCleaner;
import book_library.config.SpringCoreConfiguration;
import book_library.core.requests.Reader.GetAllReaderRequest;
import book_library.core.requests.Reader.RegisterReaderRequest;
import book_library.core.responses.Reader.GetAllReadersResponse;
import book_library.core.services.Reader.GetAllReadersService;
import book_library.core.services.Reader.RegisterReaderService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AcceptanceTest9Lesson12Task14GetAllReadersRequest {

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
    public void shouldReturnAllReaders() {
        RegisterReaderRequest registerReaderRequest1 = new RegisterReaderRequest("FirstName1", "LastName1", 11111111111L);
        getRegisterReaderService().execute(registerReaderRequest1);

        RegisterReaderRequest registerReaderRequest2 = new RegisterReaderRequest("FirstName2", "LastName2", 22222222222L);
        getRegisterReaderService().execute(registerReaderRequest2);

        RegisterReaderRequest registerReaderRequest3 = new RegisterReaderRequest("FirstName3", "LastName3", 33333333333L);
        getRegisterReaderService().execute(registerReaderRequest3);

        GetAllReaderRequest getAllReaderRequest1 = new GetAllReaderRequest();
        GetAllReadersResponse getAllReadersResponse1 = getAllReadersService().execute(getAllReaderRequest1);
        assertFalse(getAllReadersResponse1.hasErrors());
        assertEquals(3, getAllReadersResponse1.getReaders().size());
        assertEquals("FirstName1", getAllReadersResponse1.getReaders().get(0).getFirstName());
        assertEquals("LastName1", getAllReadersResponse1.getReaders().get(0).getLastName());
        assertEquals(java.util.Optional.of(11111111111L), java.util.Optional.ofNullable(getAllReadersResponse1.getReaders().get(0).getPersonalCode()));
        assertEquals("FirstName2", getAllReadersResponse1.getReaders().get(1).getFirstName());
        assertEquals("LastName2", getAllReadersResponse1.getReaders().get(1).getLastName());
        assertEquals(java.util.Optional.of(22222222222L), java.util.Optional.ofNullable(getAllReadersResponse1.getReaders().get(1).getPersonalCode()));
        assertEquals("FirstName3", getAllReadersResponse1.getReaders().get(2).getFirstName());
        assertEquals("LastName3", getAllReadersResponse1.getReaders().get(2).getLastName());
        assertEquals(java.util.Optional.of(33333333333L), java.util.Optional.ofNullable(getAllReadersResponse1.getReaders().get(2).getPersonalCode()));
    }

    private RegisterReaderService getRegisterReaderService() {
        return appContext.getBean(RegisterReaderService.class);
    }

    private GetAllReadersService getAllReadersService() {
        return appContext.getBean(GetAllReadersService.class);
    }

}
