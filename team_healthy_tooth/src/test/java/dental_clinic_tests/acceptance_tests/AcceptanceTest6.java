package dental_clinic_tests.acceptance_tests;

import dental_clinic.config.DentalClinicConfiguration;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.patient.AddPatientRequest;
import dental_clinic.core.requests.patient.ChangePersonalDataRequest;
import dental_clinic.core.requests.patient.GetPatientCardRequest;
import dental_clinic.core.responses.patient.GetPatientCardResponse;
import dental_clinic.core.services.patient.AddPatientService;
import dental_clinic.core.services.patient.ChangePersonalDataService;
import dental_clinic.core.services.patient.GetPatientCardService;
import dental_clinic_tests.DatabaseCleanerClinic;
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
        appContext = new AnnotationConfigApplicationContext(DentalClinicConfiguration.class);
        getDatabaseCleaner().clean();
    }

    @Test
    public void test(){
        PersonalData personalData1 = new PersonalData("Name", "Surname", "12345678", "25038910523");
        PersonalData personalData2 = new PersonalData("NameC", "SurnameC", "12345679", "25038910525");

        AddPatientRequest addPatientRequest1 = new AddPatientRequest(personalData1);
        addPatientService().execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest(personalData2);
        addPatientService().execute(addPatientRequest2);

        ChangePersonalDataRequest changePersonalDataRequest = new ChangePersonalDataRequest(2L, "SurnameB", "");
        changePersonalDataService().execute(changePersonalDataRequest);

        GetPatientCardRequest getPatientCardRequest = new GetPatientCardRequest(2L);
        GetPatientCardResponse getPatientCardResponse = getPatientCardService().execute(getPatientCardRequest);

        assertFalse(getPatientCardResponse.hasErrors());
        assertTrue(getPatientCardResponse.getPatient().getPersonalData().getSurname().equals("SurnameB"));
    }

    private AddPatientService addPatientService() {
        return appContext.getBean(AddPatientService.class);
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
