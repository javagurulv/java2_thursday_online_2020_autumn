package dental_clinic.core.requests.patient;

public class AddPatientRequest {

    private String name;

    private String surname;

    private String phone;

    private String personalCode;

    public AddPatientRequest() { }

    public AddPatientRequest(String name, String surname, String phone, String personalCode) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.personalCode = personalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }
}
