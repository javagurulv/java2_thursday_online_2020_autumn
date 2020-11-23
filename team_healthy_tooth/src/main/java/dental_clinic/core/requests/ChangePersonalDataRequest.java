package dental_clinic.core.requests;

public class ChangePersonalDataRequest {

    private long patientIdNumber;
    private String updatedSurname;
    private String updatedPhoneNumber;

    public ChangePersonalDataRequest(long patientIdNumber,
                                     String updatedSurname,
                                     String updatedPhoneNumber) {
        this.patientIdNumber = patientIdNumber;
        this.updatedSurname = updatedSurname;
        this.updatedPhoneNumber = updatedPhoneNumber;
    }

    public long getPatientIdNumber() {
        return patientIdNumber;
    }

    public String getUpdatedSurname() {
        return updatedSurname;
    }

    public String getUpdatedPhoneNumber() {
        return updatedPhoneNumber;
    }

}
