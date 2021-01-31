package dental_clinic_tests.acceptance_tests;

import dental_clinic_tests.DatabaseCleanerClinic;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dental_clinic.config.DentalClinicConfiguration;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.patient.AddPatientRequest;
import dental_clinic.core.requests.patient.DeletePatientRequest;
import dental_clinic.core.requests.patient.GetAllPatientsRequest;
import dental_clinic.core.responses.patient.GetAllPatientsResponse;
import dental_clinic.core.services.patient.AddPatientService;
import dental_clinic.core.services.patient.DeletePatientService;
import dental_clinic.core.services.patient.GetAllPatientsService;

import static org.junit.Assert.assertTrue;

public class AcceptanceTest2 {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(DentalClinicConfiguration.class);
        getDatabaseCleaner().clean();
    }

    @Test
    public void test(){
        PersonalData personalData1 = new PersonalData("Name", "Surname", "12345678", "25024512348");
        PersonalData personalData2 = new PersonalData("NameM", "SurnameM", "12345675", "25024512345");
        AddPatientRequest addPatientRequest1 = new AddPatientRequest(personalData1);
        AddPatientRequest addPatientRequest2 = new AddPatientRequest(personalData2);
        addPatientService().execute(addPatientRequest1);
        addPatientService().execute(addPatientRequest2);

        DeletePatientRequest deletePatientRequest = new DeletePatientRequest(8L);
        deletePatientService().execute(deletePatientRequest);

        GetAllPatientsRequest getAllPatientsRequest = new GetAllPatientsRequest();
        GetAllPatientsResponse getAllPatientsResponse = getAllPatientsService().execute(getAllPatientsRequest);

        assertTrue(getAllPatientsResponse.getPatients().size() == 2);
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

    private DatabaseCleanerClinic getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleanerClinic.class);
    }
}
