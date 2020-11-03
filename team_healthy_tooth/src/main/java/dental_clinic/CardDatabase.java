package dental_clinic;

import dental_clinic.domain.PatientPersonalData;
import dental_clinic.domain.ToothStatus;

import java.util.List;
import java.util.Optional;

public interface CardDatabase {

    boolean addPatient(PatientPersonalData patientPersonalData);

    boolean deletePatient(long id);

    boolean printDatabase();

    boolean printPatientHistory(long id);

    List<PatientPersonalData> findPatientBySurname (String surname);

    List <PatientPersonalData> findPatientByPersonalCode (String personalCode);

    boolean updateJowlData (long id, int toothNumber, Optional<String> comment, ToothStatus toothStatus);

    boolean printPatientCardForVisit(long id);

}
