package dental_clinic.UI;

import dental_clinic.CardDatabase;

class PrintPatientCardForVisitUIAction implements UIAction {

    private CardDatabase cardDatabase;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public PrintPatientCardForVisitUIAction(CardDatabase cardDatabase){
        this.cardDatabase = cardDatabase;
    }

    public void execute(){
        long id = inputCheckUtility.inputValidLong("Please enter patient id");

        if (!cardDatabase.printPatientCardForVisit(id)){
            System.out.println("Database doesn't contain patient with id " + id);
        }

    }

}

