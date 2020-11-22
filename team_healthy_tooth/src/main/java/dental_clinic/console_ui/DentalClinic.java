package dental_clinic.console_ui;

import dental_clinic.core.services.*;
import dental_clinic.database.PatientDatabase;
import dental_clinic.database.PatientDatabaseImpl;

import java.util.HashMap;
import java.util.Map;

public class DentalClinic {

    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    private Map<Integer, UIAction> menuNumberToAction;

    public DentalClinic() {
        PatientDatabase patientDatabase = new PatientDatabaseImpl();
        AddPatientValidator addPatientValidator = new AddPatientValidator();
        AddPatientService addPatientService = new AddPatientService(patientDatabase, addPatientValidator);
        DeletePatientValidator deletePatientValidator = new DeletePatientValidator();
        DeletePatientService deletePatientService = new DeletePatientService(patientDatabase, deletePatientValidator);
        GetPatientsService getPatientsService = new GetPatientsService(patientDatabase);
        GetSpecificPatientValidator getSpecificPatientValidator = new GetSpecificPatientValidator();
        GetSpecificPatientHistoryService getSpecificPatientHistoryService =
                new GetSpecificPatientHistoryService(patientDatabase, getSpecificPatientValidator);
        FindPatientsByPersonalCodeService findPatientsByPersonalCodeService =
                new FindPatientsByPersonalCodeService(patientDatabase);
        AddVisitValidator addVisitValidator = new AddVisitValidator();
        AddVisitService addVisitService = new AddVisitService(patientDatabase, addVisitValidator);
        SearchPatientRequestValidator searchPatientRequestValidator =new SearchPatientRequestValidator();
        SearchPatientService searchPatientService = new SearchPatientService(patientDatabase, searchPatientRequestValidator);

        ChangePatientPersonalDataValidator changePatientPersonalDataValidator = new ChangePatientPersonalDataValidator();
        ChangePatientPersonalDataService changePatientPersonalDataService =
                new ChangePatientPersonalDataService(patientDatabase, changePatientPersonalDataValidator);

        menuNumberToAction = new HashMap();

        //TODO exit class
        menuNumberToAction.put(1, new AddPatientUIAction(addPatientService));
        menuNumberToAction.put(2, new DeletePatientUIAction(deletePatientService));
        menuNumberToAction.put(3, new PrintPatientDatabaseUIAction(getPatientsService));
        menuNumberToAction.put(4, new PrintSpecificPatientHistoryUIAction(getSpecificPatientHistoryService));
        menuNumberToAction.put(5, new SearchPatientUIAction(searchPatientService));
        menuNumberToAction.put(6, new FindPatientByPersonalCodeUIAction(findPatientsByPersonalCodeService));
        menuNumberToAction.put(7, new AddVisitUIAction(addVisitService));
        menuNumberToAction.put(8, new ChangePatientPersonalDataUiAction(changePatientPersonalDataService));
        menuNumberToAction.put(0, new ExitUIAction());
    }

    public void run(){

        while(true) {

            printMenu();

            int userSelectedMenuNumber = inputCheckUtility.inputValidInteger("Please enter menu number: ");

            executeUIAction(userSelectedMenuNumber);
        }
    }

    private void printMenu(){
        System.out.println("\nMenu\n" +
                "1   Add patient\n" +
                "2   Delete by id\n" +
                "3   Print patients database\n" +
                "4   Print specific patient information\n" +
                "5   Search by name / surname\n" +
                "6   Find patient by personal code\n" +
                "7   Add visit\n" +
                "8   Change patient's personal data\n" +
                "0   Exit");
    }

    private void executeUIAction (int userSelectedMenuNumber) {
        UIAction uiAction = menuNumberToAction.get(userSelectedMenuNumber);
        if (uiAction != null) {
            uiAction.execute();
        } else {
            System.out.println("Menu item does not exist: " + userSelectedMenuNumber);
        }
    }

}