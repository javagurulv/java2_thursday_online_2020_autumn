package dental_clinic.database;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.requests.ChangePatientPersonalDataRequest;

import java.util.List;
import java.util.Optional;

public interface PatientDatabase {

    List<Patient> getPatients();

    void addPatient(PersonalData personalData);

    void deletePatient(long id);

    Optional <Patient> getSpecificPatientHistory(long id);

    List<Patient> findPatientByName(String name);

    List<Patient> findPatientsBySurname(String surname);

    List<Patient> findPatientsByNameAndSurname(String name, String surname);

    List<Patient> findPatientsByPersonalCode(String personalCode);

    void addVisit(long id, int toothNumber, Optional<String> comment, ToothStatus toothStatus, String doctor);

    Optional<Patient> changePatientPersonalData(ChangePatientPersonalDataRequest request);

}
