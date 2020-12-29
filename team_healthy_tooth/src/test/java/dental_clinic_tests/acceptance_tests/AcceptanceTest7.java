package dental_clinic_tests.acceptance_tests;

import dental_clinic.config.DentalClinicConfiguration;
import dental_clinic.core.domain.Doctor;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.doctor.AddDoctorRequest;
import dental_clinic.core.requests.patient.AddPatientRequest;
import dental_clinic.core.responses.doctor.AddDoctorResponse;
import dental_clinic.core.services.doctor.AddDoctorService;
import dental_clinic.core.services.patient.AddPatientService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertTrue;

public class AcceptanceTest7 {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(DentalClinicConfiguration.class);
    }

    @Test
    public void test(){
        PersonalData personalData = new PersonalData("Name", "Surname", "12345678", "25065612345");
        AddPatientRequest addPatientRequest = new AddPatientRequest(personalData);
        addPatientService().execute(addPatientRequest);

        AddDoctorRequest addDoctorRequest = new AddDoctorRequest(new Doctor("Doctor", "Haus"));
        addDoctorService().execute(addDoctorRequest);


        AddDoctorResponse addDoctorResponse = addDoctorService().execute(addDoctorRequest);
        assertTrue(addDoctorResponse.getDoctor().getName().equals("Doctor"));
    }

    private AddPatientService addPatientService() {
        return appContext.getBean(AddPatientService.class);
    }

    private AddDoctorService addDoctorService() {
        return appContext.getBean(AddDoctorService.class);
    }
}