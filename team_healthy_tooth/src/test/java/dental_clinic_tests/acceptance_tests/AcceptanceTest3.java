package dental_clinic_tests.acceptance_tests;

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
import dental_clinic.core.requests.patient.SearchPatientByPersonalCodeRequest;
import dental_clinic.core.responses.patient.GetAllPatientsResponse;
import dental_clinic.core.responses.patient.SearchPatientByPersonalCodeResponse;
import dental_clinic.core.services.patient.AddPatientService;
import dental_clinic.core.services.patient.DeletePatientService;
import dental_clinic.core.services.patient.GetAllPatientsService;
import dental_clinic.core.services.patient.SearchPatientsByPersonalCodeService;

import static org.junit.Assert.assertTrue;

public class AcceptanceTest3 {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(DentalClinicConfiguration.class);
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

        SearchPatientByPersonalCodeRequest searchPatientByPersonalCodeRequest = new SearchPatientByPersonalCodeRequest("25038910523");
        SearchPatientByPersonalCodeResponse searchPatientByPersonalCodeResponse = searchPatientsByPersonalCodeService().execute(searchPatientByPersonalCodeRequest);

        assertTrue(getAllPatientsResponse.getPatients().size() == 1);
        assertTrue(searchPatientByPersonalCodeResponse.getFoundPatient().equals(patient));

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

    private SearchPatientsByPersonalCodeService searchPatientsByPersonalCodeService(){
        return appContext.getBean(SearchPatientsByPersonalCodeService.class);
    }

}
