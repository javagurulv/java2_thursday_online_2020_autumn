package dental_clinic.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PatientPersonalData {

    long id;
    private final String name;
    private String surname;
    private String phone;
    private final String personalCode;
    private List<String> attendingDoctors;

    public PatientPersonalData(String name, String surname, String phone, String personalCode, String...attendingDoctors){
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.personalCode = personalCode;
        this.attendingDoctors = new ArrayList<>(Arrays.asList(attendingDoctors));
    }

    public long getId (){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public List<String> getAttendingDoctors() {
        return attendingDoctors;
    }

    public void setId (long id){
        this.id = id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void addAttendingDoctor(String attendingDoctor) {
        this.attendingDoctors.add(attendingDoctor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientPersonalData patientPersonalData = (PatientPersonalData) o;
        return Objects.equals(name.toLowerCase(), patientPersonalData.name.toLowerCase()) &&
                Objects.equals(surname.toLowerCase(), patientPersonalData.surname.toLowerCase()) &&
                Objects.equals(phone, patientPersonalData.phone) &&
                Objects.equals(personalCode, patientPersonalData.personalCode) &&
                Objects.equals(attendingDoctors, patientPersonalData.attendingDoctors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, phone, personalCode, attendingDoctors);
    }

    @Override
    public String toString() {
        return "\nPatient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", personalCode='" + personalCode + '\'' +
                ", attendingDoctors=" + attendingDoctors + "\n";
    }
}
