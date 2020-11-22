package dental_clinic.core.requests;

import dental_clinic.core.domain.PersonalData;

public class ChangePatientPersonalDataRequest {

    long idToChange;
    String updatedName;
    String updatedSurname;
    String updatedPhoneNumber;

    public ChangePatientPersonalDataRequest(
            long idToChange, String updatedName, String updatedSurname, String updatedPhoneNumber) {
        this.idToChange = idToChange;
        this.updatedName = updatedName;
        this.updatedSurname = updatedSurname;
        this.updatedPhoneNumber = updatedPhoneNumber;
    }

    public long getIdToChange() {
        return idToChange;
    }

    public String getUpdatedName() {
        return updatedName;
    }

    public String getUpdatedSurname() {
        return updatedSurname;
    }

    public String getUpdatedPhoneNumber() {
        return updatedPhoneNumber;
    }
}

