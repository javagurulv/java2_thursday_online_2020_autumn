package dental_clinic_tests.acceptance_tests;

import dental_clinic.config.DentalClinicSpringCoreConfiguration;
import dental_clinic.core.domain.OrderingDirection;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.Ordering;
import dental_clinic.core.requests.Paging;
import dental_clinic.core.requests.patient.AddPatientRequest;
import dental_clinic.core.requests.patient.ChangePersonalDataRequest;
import dental_clinic.core.requests.patient.GetAllPatientsRequest;
import dental_clinic.core.requests.patient.SearchPatientRequest;
import dental_clinic.core.responses.patient.GetAllPatientsResponse;
import dental_clinic.core.responses.patient.SearchPatientResponse;
import dental_clinic.core.services.patient.AddPatientService;
import dental_clinic.core.services.patient.ChangePersonalDataService;
import dental_clinic.core.services.patient.GetAllPatientsService;
import dental_clinic.core.DatabaseCleanerClinic;
import dental_clinic.core.services.patient.SearchPatientService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertTrue;

public class AcceptanceTest4 {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(DentalClinicSpringCoreConfiguration.class);
        getDatabaseCleaner().clean();
    }

    @Test
    public void test(){
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("Name", "Surname", "12345678", "25038910523");
        AddPatientRequest addPatientRequest2 = new AddPatientRequest("NameB", "SurnameA", "12345675", "25038910528");
        addPatientService().execute(addPatientRequest1);
        addPatientService().execute(addPatientRequest2);

        SearchPatientRequest searchPatientRequest =
                new SearchPatientRequest("Surname",
                        "name", OrderingDirection.ASC,
                        1, 100);
        SearchPatientResponse searchPatientResponse = searchPatientService().execute(searchPatientRequest);

        Long id = searchPatientResponse.getPatients().get(0).getId();

        ChangePersonalDataRequest changePersonalDataRequest = new ChangePersonalDataRequest(id, "SurnameB", "");
        changePersonalDataService().execute(changePersonalDataRequest);

        GetAllPatientsRequest getAllPatientsRequest = new GetAllPatientsRequest();
        GetAllPatientsResponse getAllPatientsResponse = getAllPatientsService().execute(getAllPatientsRequest);

        assertTrue(getAllPatientsResponse.getPatients().size() == 2);

        assertTrue(getAllPatientsResponse.getPatients().get(0).getPersonalData().getSurname().equals("SurnameB"));
        assertTrue(getAllPatientsResponse.getPatients().get(0).getPersonalData().getPhone().equals("12345678"));

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

    private GetAllPatientsService getAllPatientsService() {
        return appContext.getBean(GetAllPatientsService.class);
    }

    private DatabaseCleanerClinic getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleanerClinic.class);
    }
}
