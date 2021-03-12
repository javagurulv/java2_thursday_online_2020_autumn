package dental_clinic.core.database.patient;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.domain.ToothStatus;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {

    List<Patient> getPatients();

    void addPatient(PersonalData personalData);

    Optional<PersonalData> getPersonalDataById(Long id);

    List<PersonalData> findPatientsBySurname(String surname);

    List<PersonalData> findPatientsByPersonalCode(String personalCode);

    boolean containsPatientWithSpecificId(Long id);

    Optional<Patient> getPatientCard(Long id);

    void changeSurname(Long idToSearch, String updatedSurname);

    void changePhone(Long idToSearch, String updatedSurname);

    boolean containsSpecificPersonalData(PersonalData personalData);

    void updateJowl(Long patientId, int toothNumber, ToothStatus toothStatus);
}
