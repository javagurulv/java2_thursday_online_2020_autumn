package dental_clinic_tests.acceptance_tests;

import dental_clinic.config.DentalClinicConfiguration;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.patient.AddPatientRequest;
import dental_clinic.core.requests.patient.GetAllPatientsRequest;
import dental_clinic.core.responses.patient.GetAllPatientsResponse;
import dental_clinic.core.services.patient.AddPatientService;
import dental_clinic.core.services.patient.GetAllPatientsService;
import dental_clinic_tests.DatabaseCleanerClinic;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertTrue;

public class AcceptanceTest1 {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(DentalClinicConfiguration.class);
    }

    @Test
    public void test(){
        getDatabaseCleaner().clean();
        PersonalData personalData = new PersonalData("Name", "Surname", "12345678", "25065612345");
        AddPatientRequest addPatientRequest = new AddPatientRequest(personalData);
        addPatientService().execute(addPatientRequest);
        addPatientService().execute(addPatientRequest);

        GetAllPatientsRequest getAllPatientsRequest = new GetAllPatientsRequest();
        GetAllPatientsResponse getAllPatientsResponse = getAllPatientsService().execute(getAllPatientsRequest);

        assertTrue(getAllPatientsResponse.getPatients().size() == 1);
    }

    private AddPatientService addPatientService() {
        return appContext.getBean(AddPatientService.class);
    }

    private GetAllPatientsService getAllPatientsService() {
        return appContext.getBean(GetAllPatientsService.class);
    }

    private DatabaseCleanerClinic getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleanerClinic.class);
    }
}
