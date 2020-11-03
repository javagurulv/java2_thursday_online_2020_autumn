package dental_clinic.UI;

import dental_clinic.CardDatabase;

class DeletePatientUIAction implements UIAction {

    private CardDatabase cardDatabase;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public DeletePatientUIAction(CardDatabase cardDatabase){
        this.cardDatabase = cardDatabase;
    }

    public void execute(){

        long id = inputCheckUtility.inputValidLong("Please enter patients id");

        if (cardDatabase.deletePatient(id)){
            System.out.println("Patient with id " + id + " is deleted");
        }else{
            System.out.println("Database doesn't contain patient with id " + id);
        }
    }

}

