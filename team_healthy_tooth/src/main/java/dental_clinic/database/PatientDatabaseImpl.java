package dental_clinic.database;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.domain.Visit;
import dental_clinic.core.requests.ChangePatientPersonalDataRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PatientDatabaseImpl implements PatientDatabase {

    private Long id= 1L;
    private List<Patient> patientList = new ArrayList<>();

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
    public void deletePatient(long id) {
        for (int i = 0; i < patientList.size(); i++){
            if (patientList.get(i).getPersonalData().getId() == id){
                patientList.remove(i);
            }
        }
    }

    @Override
    public Optional <Patient> getSpecificPatientHistory(long id) {
            for (int i = 0; i < patientList.size(); i++){
                if (isSpecificPatient(i, id)){
                    return Optional.of(patientList.get(i));
                }
            }
            return Optional.empty();
    }

    @Override
    public List<Patient> findPatientByName(String name) {
        return patientList.stream()
                .filter(patient -> patient.getPersonalData().getName().toLowerCase().startsWith(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> findPatientsBySurname(String surname) {
        return patientList.stream()
                .filter(patient -> patient.getPersonalData().getSurname().toLowerCase().startsWith(surname.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> findPatientsByNameAndSurname(String name, String surname) {
        return patientList.stream()
                .filter(patient ->
                        patient.getPersonalData().getName().toLowerCase().startsWith(name.toLowerCase()) &&
                                patient.getPersonalData().getSurname().toLowerCase().startsWith(surname.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> findPatientsByPersonalCode(String personalCode) {
        return patientList.stream()
                .filter(patient -> patient.getPersonalData().getPersonalCode().equals(personalCode))
                .collect(Collectors.toList());
    }

    @Override
    public void addVisit(long id, int toothNumber, Optional<String> comment, ToothStatus toothStatus, String doctor) {
        for (int i = 0; i < patientList.size(); i++){
            if (isSpecificPatient(i, id)){
                Visit visit = new Visit(toothNumber, comment, toothStatus, doctor);
                patientList.get(i).addVisit(visit);
                patientList.get(i).updateJowl(toothNumber, toothStatus);
            }
        }
    }

    @Override
    public Optional<Patient> changePatientPersonalData(ChangePatientPersonalDataRequest request) {
        Optional<Patient> updatedPatientResult = Optional.empty();
        for (Patient patient : patientList) {
            long foundId = patient.getPersonalData().getId();
            if (foundId == id) {
                patient.updatePersonalData(request);
                updatedPatientResult = Optional.of(patient);
            }
        }
        return updatedPatientResult;
    }

    private boolean isSpecificPatient (int index, long id) {
        return patientList.get(index).getPersonalData().getId() == id;
    }

}
