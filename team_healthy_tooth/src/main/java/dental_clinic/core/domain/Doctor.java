package dental_clinic.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Doctor {

    private Long id;
    private String name;
    private String surname;
    private String phone;
    private boolean isEmployed;
    private WorkGraphic workGraphic = new WorkGraphic();
    private List<Visit> visits = new ArrayList<>();

    public Doctor() { }

    public Doctor(String name, String surname, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        isEmployed = true;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public boolean getIsEmployed() {
        return isEmployed;
    }

    public void setEmployed(boolean employed) {
        isEmployed = employed;
    }

    public WorkGraphic getWorkGraphic() {
        return workGraphic;
    }

    public void setWorkGraphic(WorkGraphic workGraphic) {
        this.workGraphic = workGraphic;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public void addVisit(Visit visit){
        visits.add(visit);
    }

    public boolean filledNameAndSurname() {
        return name != null && !name.isEmpty() && surname != null && !surname.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(name, doctor.name) && Objects.equals(surname, doctor.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public String toString() {
        return "Doctor: " +
                "id: " + id + "\n" +
                "Dr: '" + name + " " + surname + "\n"
                +workGraphic
                +"Visits: " + visits + "\n\n";
    }
}
