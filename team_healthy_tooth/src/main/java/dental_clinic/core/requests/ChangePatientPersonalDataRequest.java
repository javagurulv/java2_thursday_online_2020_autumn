package dental_clinic.core.requests;

public class ChangePatientPersonalDataRequest {

    long idToSearch;
    String updatedName;
    String updatedSurname;
    String updatedPhoneNumber;

    public ChangePatientPersonalDataRequest(
            long idToSearch, String updatedName, String updatedSurname, String updatedPhoneNumber) {
        this.idToSearch = idToSearch;
        this.updatedName = updatedName;
        this.updatedSurname = updatedSurname;
        this.updatedPhoneNumber = updatedPhoneNumber;
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

