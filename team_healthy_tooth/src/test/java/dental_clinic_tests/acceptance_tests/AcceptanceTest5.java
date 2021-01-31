package dental_clinic_tests.acceptance_tests;

import dental_clinic.config.DentalClinicConfiguration;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.patient.AddPatientRequest;
import dental_clinic.core.requests.patient.ChangePersonalDataRequest;
import dental_clinic.core.requests.patient.GetAllPatientsRequest;
import dental_clinic.core.responses.patient.GetAllPatientsResponse;
import dental_clinic.core.services.patient.AddPatientService;
import dental_clinic.core.services.patient.ChangePersonalDataService;
import dental_clinic.core.services.patient.GetAllPatientsService;
import dental_clinic_tests.DatabaseCleanerClinic;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertTrue;

public class AcceptanceTest5 {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(DentalClinicConfiguration.class);
        getDatabaseCleaner().clean();
    }

    @Test
    public void test(){
        PersonalData personalData1 = new PersonalData("Name", "Surname", "12345678", "25038910523");

        AddPatientRequest addPatientRequest1 = new AddPatientRequest(personalData1);
        addPatientService().execute(addPatientRequest1);

        ChangePersonalDataRequest changePersonalDataRequest = new ChangePersonalDataRequest(1L, "SurnameB", "");
        changePersonalDataService().execute(changePersonalDataRequest);

        ChangePersonalDataRequest changePersonalDataRequest2 = new ChangePersonalDataRequest(1L, "SurnameK", "87654321");
        changePersonalDataService().execute(changePersonalDataRequest2);

        GetAllPatientsRequest getAllPatientsRequest = new GetAllPatientsRequest();
        GetAllPatientsResponse getAllPatientsResponse = getAllPatientsService().execute(getAllPatientsRequest);

        assertTrue(getAllPatientsResponse.getPatients().size() == 1);

        assertTrue(getAllPatientsResponse.getPatients().get(0).getPersonalData().getSurname().equals("SurnameK"));
        assertTrue(getAllPatientsResponse.getPatients().get(0).getPersonalData().getPhone().equals("87654321"));

    }

    private AddPatientService addPatientService() {
        return appContext.getBean(AddPatientService.class);
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
