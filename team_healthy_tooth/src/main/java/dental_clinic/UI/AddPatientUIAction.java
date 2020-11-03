package dental_clinic.UI;

import dental_clinic.CardDatabase;
import dental_clinic.domain.PatientPersonalData;

class AddPatientUIAction implements UIAction {

    private CardDatabase cardDatabase;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public AddPatientUIAction(CardDatabase cardDatabase){
        this.cardDatabase = cardDatabase;
    }

    public void execute(){
        String name = inputCheckUtility.inputValidString("Please enter name");
        String surname = inputCheckUtility.inputValidString("Please enter surname");
        String phone = inputCheckUtility.inputValidPhone("Please enter phone");
        String personalCode = inputCheckUtility.inputValidPersonalCode("Please enter personal code");
        String doctor = inputCheckUtility.inputValidString("Please enter doctor's surname");

        PatientPersonalData patientPersonalData = new PatientPersonalData(name, surname, phone, personalCode, doctor);

        if (cardDatabase.addPatient(patientPersonalData)){
            System.out.println("Patient added");
        }else{
            System.out.println("The same patient is in database");
        }

    }

}

