package dental_clinic_tests.acceptance_tests;

import dental_clinic.core.domain.OrderingDirection;
import dental_clinic.core.requests.Ordering;
import dental_clinic.core.requests.Paging;
import dental_clinic.core.requests.patient.SearchPatientRequest;
import dental_clinic.core.responses.patient.SearchPatientResponse;
import dental_clinic.core.services.patient.SearchPatientService;
import dental_clinic_tests.DatabaseCleanerClinic;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dental_clinic.config.DentalClinicConfiguration;
import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.patient.AddPatientRequest;
import dental_clinic.core.requests.patient.DeletePatientRequest;
import dental_clinic.core.requests.patient.GetAllPatientsRequest;
import dental_clinic.core.responses.patient.GetAllPatientsResponse;
import dental_clinic.core.services.patient.AddPatientService;
import dental_clinic.core.services.patient.DeletePatientService;
import dental_clinic.core.services.patient.GetAllPatientsService;

import static org.junit.Assert.assertTrue;

public class AcceptanceTest3 {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(DentalClinicConfiguration.class);
        getDatabaseCleaner().clean();
    }

    @Test
    public void test(){
        PersonalData personalData1 = new PersonalData("Name", "Surname", "12345678", "25038910523");
        Patient patient = new Patient(personalData1);
        PersonalData personalData2 = new PersonalData("NameA", "SurnameB", "12345675", "25038910527");
        AddPatientRequest addPatientRequest1 = new AddPatientRequest(personalData1);
        AddPatientRequest addPatientRequest2 = new AddPatientRequest(personalData2);
        addPatientService().execute(addPatientRequest1);
        addPatientService().execute(addPatientRequest2);

        DeletePatientRequest deletePatientRequest = new DeletePatientRequest(2L);
        deletePatientService().execute(deletePatientRequest);

        GetAllPatientsRequest getAllPatientsRequest = new GetAllPatientsRequest();
        GetAllPatientsResponse getAllPatientsResponse = getAllPatientsService().execute(getAllPatientsRequest);

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("25038910523", new Ordering("name", OrderingDirection.ASC), new Paging(1, 100));
        SearchPatientResponse searchPatientResponse = searchPatientService().execute(searchPatientRequest);

        assertTrue(getAllPatientsResponse.getPatients().size() == 1);
        assertTrue(searchPatientResponse.getPatients().get(0).equals(patient));

    }

    private AddPatientService addPatientService() {
        return appContext.getBean(AddPatientService.class);
    }

    private DeletePatientService deletePatientService(){
        return appContext.getBean(DeletePatientService.class);
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
