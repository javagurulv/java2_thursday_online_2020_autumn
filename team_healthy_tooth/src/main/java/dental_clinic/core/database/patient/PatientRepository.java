package dental_clinic.core.database.patient;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.domain.Visit;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {

    List<PersonalData> getPatients();

    void addPatient(PersonalData personalData);

    void deletePatient(Long id);

    Optional <Patient> getSpecificPatientHistory(Long id);

    List<PersonalData> findPatientsBySurname(String surname);

    List<PersonalData> findPatientsByPersonalCode(String personalCode);

    void addVisit(Long id, Visit newVisit);

    boolean containsPatientWithSpecificId(Long id);

    Optional<Patient> getPatientCard(Long id);

    void changeSurname(Long idToSearch, String updatedSurname);

    void changePhone(Long idToSearch, String updatedSurname);

    Optional<Patient> findPatientByIdNumber(Long idToSearch);

    boolean containsSpecificPersonalData(PersonalData personalData);
}
