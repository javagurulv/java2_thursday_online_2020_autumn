package dental_clinic_tests.acceptance_tests;

import dental_clinic.config.DentalClinicSpringCoreConfiguration;
import dental_clinic.core.domain.OrderingDirection;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.Ordering;
import dental_clinic.core.requests.Paging;
import dental_clinic.core.requests.patient.AddPatientRequest;
import dental_clinic.core.requests.patient.ChangePersonalDataRequest;
import dental_clinic.core.requests.patient.GetPatientCardRequest;
import dental_clinic.core.requests.patient.SearchPatientRequest;
import dental_clinic.core.responses.patient.GetPatientCardResponse;
import dental_clinic.core.responses.patient.SearchPatientResponse;
import dental_clinic.core.services.patient.AddPatientService;
import dental_clinic.core.services.patient.ChangePersonalDataService;
import dental_clinic.core.services.patient.GetPatientCardService;
import dental_clinic.core.DatabaseCleanerClinic;
import dental_clinic.core.services.patient.SearchPatientService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AcceptanceTest6 {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(DentalClinicSpringCoreConfiguration.class);
        getDatabaseCleaner().clean();
    }

    @Test
    public void test(){
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("Name", "Surname", "12345678", "25038910523");
        addPatientService().execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest("NameC", "SurnameC", "12345679", "25038910525");
        addPatientService().execute(addPatientRequest2);

        SearchPatientRequest searchPatientRequest =
                new SearchPatientRequest("SurnameC",
                        "name", OrderingDirection.ASC, 1, 100);
        SearchPatientResponse searchPatientResponse = searchPatientService().execute(searchPatientRequest);

        Long id = searchPatientResponse.getPatients().get(0).getId();

        ChangePersonalDataRequest changePersonalDataRequest = new ChangePersonalDataRequest(id, "SurnameB", "");
        changePersonalDataService().execute(changePersonalDataRequest);

        GetPatientCardRequest getPatientCardRequest = new GetPatientCardRequest(id);
        GetPatientCardResponse getPatientCardResponse = getPatientCardService().execute(getPatientCardRequest);

        assertFalse(getPatientCardResponse.hasErrors());
        assertTrue(getPatientCardResponse.getPatient().getPersonalData().getSurname().equals("SurnameB"));
    }

    private AddPatientService addPatientService() {
        return appContext.getBean(AddPatientService.class);
    }

    private SearchPatientService searchPatientService() {
        return appContext.getBean(SearchPatientService.class);
    }

    private ChangePersonalDataService changePersonalDataService(){
        return appContext.getBean(ChangePersonalDataService.class);
    }

    private GetPatientCardService getPatientCardService() {
        return appContext.getBean(GetPatientCardService.class);
    }

    private DatabaseCleanerClinic getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleanerClinic.class);
    }
}
