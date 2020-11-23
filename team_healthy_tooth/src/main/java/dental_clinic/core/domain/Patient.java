package dental_clinic.core.domain;

import java.util.*;

public class Patient {

    private PersonalData personalData;
    private Jowl jowl = new Jowl();
    private List <Visit> visits = new ArrayList<>();

    public Patient(PersonalData personalData){
        this.personalData = personalData;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public Jowl getJowl() {
        return jowl;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void addVisit(Visit visit){
        visits.add(visit);
    }

    public void updateJowl(int toothNumber, ToothStatus toothStatus){
        jowl.updateJowl(toothNumber, toothStatus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(personalData, patient.personalData) &&
                Objects.equals(jowl, patient.jowl) &&
                Objects.equals(visits, patient.visits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalData, jowl, visits);
    }

    @Override
    public String toString() {
        return "\nPatient{" +
                personalData +
                ", jowl=" + jowl +
                ", visits=" + visits +
                '}';
    }
}
