package dental_clinic.UI;

import dental_clinic.CardDatabase;
import dental_clinic.domain.PatientPersonalData;

import java.util.List;

class FindPatientBySurnameUIAction implements UIAction {

    private CardDatabase cardDatabase;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public FindPatientBySurnameUIAction(CardDatabase cardDatabase){
        this.cardDatabase = cardDatabase;
    }

    public void execute(){
        String surname = inputCheckUtility.inputValidString("Please enter surname");

        List<PatientPersonalData> result = cardDatabase.findPatientBySurname(surname);

        if (result.isEmpty()){
            System.out.println("Database doesn't contain patient with surname " + surname);
        }else{
            result.forEach(System.out::println);
        }

    }

}

