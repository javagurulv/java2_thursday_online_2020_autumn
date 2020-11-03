package dental_clinic.UI;

import dental_clinic.CardDatabase;
import dental_clinic.domain.ToothStatus;

import java.util.Optional;

class UpdateJowlDataUIAction implements UIAction {

    private CardDatabase cardDatabase;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public UpdateJowlDataUIAction(CardDatabase cardDatabase){
        this.cardDatabase = cardDatabase;
    }

    public void execute(){
        long id = inputCheckUtility.inputValidLong("Please enter patient's id");

        int toothNumber = inputCheckUtility.inputValidInteger("Please input tooth number");

        Optional<String> comment = inputCheckUtility.inputComment("Please input comment if necessary or press enter");

        ToothStatus toothStatus = inputToothStatus();

        if (!cardDatabase.updateJowlData(id, toothNumber, comment, toothStatus)){
            System.out.println("Not correct input");
        }

    }

    ToothStatus inputToothStatus(){
        System.out.println(
                "1   KARIES\n" +
                        "2   PLOMBA\n" +
                        "3   SAKNE\n" +
                        "4   KRONITIS\n" +
                        "5   KLAMERS\n" +
                        "6   NAV_ZOBA\n" +
                        "7   FASETE\n" +
                        "8   NONEMAMA_PROTEZE\n" +
                        "9   KRONITIS_AR_FAS\n" +
                        "10  PLAST_KRONITIS\n" +
                        "11  TILTINI\n" +
                        "12  HEALTHY\n");

        int variant = 0;
        while (variant < 1 || variant > 12)
        variant = inputCheckUtility.inputValidInteger("Please choose tooth status");
        switch (variant) {
            case 1: return ToothStatus.KARIES;
            case 2: return ToothStatus.PLOMBA;
            case 3: return ToothStatus.SAKNE;
            case 4: return ToothStatus.KRONITIS;
            case 5: return ToothStatus.KLAMERS;
            case 6: return ToothStatus.NAV_ZOBA;
            case 7: return ToothStatus.FASETE;
            case 8: return ToothStatus.NONEMAMA_PROTEZE;
            case 9: return ToothStatus.KRONITIS_AR_FAS;
            case 10: return ToothStatus.PLAST_KRONITIS;
            case 11: return ToothStatus.TILTINI;
            case 12: return ToothStatus.HEALTHY;
        }
        return ToothStatus.HEALTHY;
    }

}

