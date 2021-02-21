package dental_clinic_tests.acceptance_tests;

import dental_clinic.core.DatabaseCleanerClinic;
import dental_clinic.config.DentalClinicSpringCoreConfiguration;
import dental_clinic.core.domain.OrderingDirection;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.Ordering;
import dental_clinic.core.requests.Paging;
import dental_clinic.core.requests.patient.AddPatientRequest;
import dental_clinic.core.requests.patient.GetAllPatientsRequest;
import dental_clinic.core.requests.patient.SearchPatientRequest;
import dental_clinic.core.responses.patient.GetAllPatientsResponse;
import dental_clinic.core.responses.patient.SearchPatientResponse;
import dental_clinic.core.services.patient.AddPatientService;
import dental_clinic.core.services.patient.GetAllPatientsService;
import dental_clinic.core.services.patient.SearchPatientService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertTrue;

public class AcceptanceTest3 {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(DentalClinicSpringCoreConfiguration.class);
        getDatabaseCleaner().clean();
    }

    @Test
    public void test(){
        PersonalData personalData1 = new PersonalData("Name", "Surname", "12345678", "25038910523");
        PersonalData personalData2 = new PersonalData("NameA", "SurnameB", "12345675", "25038910527");
        AddPatientRequest addPatientRequest1 = new AddPatientRequest(personalData1);
        AddPatientRequest addPatientRequest2 = new AddPatientRequest(personalData2);
        addPatientService().execute(addPatientRequest1);
        addPatientService().execute(addPatientRequest2);

        GetAllPatientsRequest getAllPatientsRequest = new GetAllPatientsRequest();
        GetAllPatientsResponse getAllPatientsResponse = getAllPatientsService().execute(getAllPatientsRequest);

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("25038910523", new Ordering("name", OrderingDirection.ASC), new Paging(1, 100));
        SearchPatientResponse searchPatientResponse = searchPatientService().execute(searchPatientRequest);
        getAllPatientsResponse.getPatients().forEach(System.out::println);
        assertTrue(getAllPatientsResponse.getPatients().size() == 2);//???????????????????????????????????
        assertTrue(searchPatientResponse.getPatients().get(0).getPersonalCode().equals("25038910523"));
    }

    private AddPatientService addPatientService() {
        return appContext.getBean(AddPatientService.class);
    }

    private GetAllPatientsService getAllPatientsService() {
        return appContext.getBean(GetAllPatientsService.class);
    }

    private SearchPatientService searchPatientService(){
        return appContext.getBean(SearchPatientService.class);
    }

    private DatabaseCleanerClinic getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleanerClinic.class);
    }
}