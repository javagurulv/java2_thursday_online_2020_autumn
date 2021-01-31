package dental_clinic.core.database.patient;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.domain.Visit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/*
//@Component
public class InMemoryPatientRepositoryImpl implements PatientRepository {

    private Long id= 1L;
    private final List<Patient> patientList = new ArrayList<>();

    @Override
    public List<Patient> getPatients(){
        return patientList;
    }

    @Override
    public void addPatient(PersonalData personalData) {
            personalData.setId(id);
            patientList.add(new Patient(personalData));
            id++;
    }

    @Override
    public void deletePatient(Long id) {
        for (int i = 0; i < patientList.size(); i++){
            if (patientList.get(i).getPersonalData().getId().equals(id)){
                patientList.remove(i);
            }
        }
    }

    @Override
    public Optional <Patient> getSpecificPatientHistory(Long id) {
            for (int i = 0; i < patientList.size(); i++){
                if (isSpecificPatient(i, id)){
                    return Optional.of(patientList.get(i));
                }
            }
            return Optional.empty();
    }

    @Override
    public List<Patient> findPatientsBySurname(String surname) {
        return patientList.stream()
                .filter(patient -> patient.getPersonalData().getSurname().toLowerCase().startsWith(surname.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> findPatientsByPersonalCode(String personalCode) {
        return patientList.stream()
                .filter(patient -> patient.getPersonalData().getPersonalCode().equals(personalCode))
                .collect(Collectors.toList());
    }

    @Override
    public void addVisit(Long id, Visit newVisit) {
        for (int i = 0; i < patientList.size(); i++){
            if (isSpecificPatient(i, id)){
                Visit visit = new Visit();
                        new Visit(id, newVisit.getToothNumber(), newVisit.getComment(), newVisit.getToothStatus(),
                                newVisit.getDoctor(), newVisit.getManipulations(), newVisit.getDate());
                patientList.get(i).addVisit(visit);
                patientList.get(i).updateJowl(newVisit.getToothNumber(), ToothStatus.HEALTHY);
                        newVisit.getToothStatus());
            }
        }
    }

    @Override
    public Optional<Patient> getPatientCard(Long id) {
        return patientList.stream()
                .filter(patient -> patient.getPersonalData().getId().equals(id))
                .findAny();
    }

    private boolean isSpecificPatient (int index, Long id) {
        return patientList.get(index).getPersonalData().getId().equals(id);
    }

    @Override
    public boolean containsPatientWithSpecificId(Long id) {
        for (Patient patient : patientList) {
            if (patient.getPersonalData().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void changeSurname(Long idToSearch, String updatedSurname) {
        for (Patient patient : patientList) {
            if (patient.getPersonalData().getId().equals(idToSearch)) {
                patient.getPersonalData().setSurname(updatedSurname);
            }
        }
    }

    @Override
    public void changePhone(Long idToSearch, String updatedPhone) {
        for (Patient patient : patientList) {
            if (patient.getPersonalData().getId().equals(idToSearch)) {
                patient.getPersonalData().setPhone(updatedPhone);
            }
        }
    }

    @Override
    public Optional<Patient> findPatientByIdNumber(Long idToSearch) {
        Optional<Patient> result = Optional.empty();
        for (Patient patient : patientList) {
            Long foundId = patient.getPersonalData().getId();
            if (foundId.equals(idToSearch)) {
                result = Optional.of(patient);
            }
        }
        return result;
    }

    @Override
    public boolean containsSpecificPersonalData(PersonalData personalData){
        return patientList.stream()
                .map(Patient::getPersonalData)
                .anyMatch(personalData1 -> personalData1.getPersonalCode().equals(personalData.getPersonalCode()));
    }

}*/
