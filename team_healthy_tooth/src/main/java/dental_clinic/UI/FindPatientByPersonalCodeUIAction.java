package dental_clinic.UI;

import dental_clinic.CardDatabase;
import dental_clinic.domain.PatientPersonalData;

import java.util.List;

class FindPatientByPersonalCodeUIAction implements UIAction {

    private CardDatabase cardDatabase;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public FindPatientByPersonalCodeUIAction(CardDatabase cardDatabase){
        this.cardDatabase = cardDatabase;
    }

    public void execute(){

        String personalCode = inputCheckUtility.inputValidPersonalCode("Please enter personal code");

        List<PatientPersonalData> result = cardDatabase.findPatientByPersonalCode(personalCode);

        if (result.isEmpty()){
            System.out.println("Database doesn't contain patient with personal code " + personalCode);
        }else{
            result.forEach(System.out::println);
        }

    }

}

