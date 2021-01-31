package dental_clinic_tests.acceptance_tests;

import dental_clinic.config.DentalClinicConfiguration;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.plannedVisit.AddPlannedVisitRequest;
import dental_clinic.core.requests.plannedVisit.GetPlannedVisitsRequest;
import dental_clinic.core.responses.planned_visit.GetPlannedVisitsResponse;
import dental_clinic.core.services.planned_visit.AddPlannedVisitService;
import dental_clinic.core.services.planned_visit.GetPlannedVisitsService;
import dental_clinic_tests.DatabaseCleanerClinic;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertTrue;

public class AcceptanceTest8 {

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

        AddPlannedVisitRequest addPlannedVisitRequest1 = new AddPlannedVisitRequest(true, "25-08-2021 14:30", personalData1, 1L);
        AddPlannedVisitRequest addPlannedVisitRequest2 = new AddPlannedVisitRequest(true,"25-09-2021 15:30", personalData2, 1L);
        AddPlannedVisitRequest addPlannedVisitRequest3 = new AddPlannedVisitRequest(false,"25-08-2021 14:30", personalData2, 1L);

        addPlannedVisitService().execute(addPlannedVisitRequest1);
        addPlannedVisitService().execute(addPlannedVisitRequest2);
        addPlannedVisitService().execute(addPlannedVisitRequest3);

        GetPlannedVisitsRequest getPlannedVisitsRequest = new GetPlannedVisitsRequest();
        GetPlannedVisitsResponse getPlannedVisitsResponse = getPlannedVisitsService().execute(getPlannedVisitsRequest);
System.out.println();
        assertTrue(getPlannedVisitsResponse.getPlannedVisits().size() == 2);
    }

    private AddPlannedVisitService addPlannedVisitService() {
        return appContext.getBean(AddPlannedVisitService.class);
    }

    private GetPlannedVisitsService getPlannedVisitsService() {
        return appContext.getBean(GetPlannedVisitsService.class);
    }

    private DatabaseCleanerClinic getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleanerClinic.class);
    }
}
