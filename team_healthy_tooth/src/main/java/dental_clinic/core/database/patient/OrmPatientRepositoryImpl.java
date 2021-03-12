package dental_clinic.core.database.patient;

import dental_clinic.core.domain.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmPatientRepositoryImpl implements PatientRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Patient> getPatients() {
        List <PersonalData> personalDataList = getPersonalData();
        List<Patient> patientList = new ArrayList<>();
        for (PersonalData personalData : personalDataList) {
            String sql = "SELECT v FROM Visit v WHERE patient_id =" + personalData.getId();
            List<Visit>visits = sessionFactory.getCurrentSession()
                    .createQuery(sql, Visit.class)
                    .getResultList();

            Query query1 = sessionFactory.getCurrentSession().createQuery(
                    "SELECT j FROM JowlEntity j WHERE patient_id = :id");
            query1.setParameter("id", personalData.getId());
            JowlEntity jowlEntity = (JowlEntity) query1.getResultList().get(0);
            Patient patient = new Patient(personalData, jowlEntity, visits);
            patientList.add(patient);
        }
        return patientList;
    }

    @Override
    public void addPatient(PersonalData personalData) {
        sessionFactory.getCurrentSession().save(personalData);
        Long id = getPersonalData().get(getPersonalData().size() - 1).getId();
        JowlEntity jowlEntity = new JowlEntity(getPersonalDataById(id).get());
        sessionFactory.getCurrentSession().save(jowlEntity);
    }

    @Override
    public Optional<PersonalData> getPersonalDataById(Long id) {
        PersonalData personalData = sessionFactory.getCurrentSession().get(PersonalData.class, id);
        return Optional.ofNullable(personalData);
    }

    @Override
    public List<PersonalData> findPatientsBySurname(String surname) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM PersonalData p WHERE surname = :surname");
        query.setParameter("surname", surname);
        return query.getResultList();
    }

    @Override
    public List<PersonalData> findPatientsByPersonalCode(String personalCode) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM PersonalData p WHERE personalCode = :personalCode");
        query.setParameter("personalCode", personalCode);
        return query.getResultList();
    }

    @Override
    public boolean containsPatientWithSpecificId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM PersonalData p WHERE id = :id");
        query.setParameter("id", id);
        return !query.getResultList().isEmpty();
    }

    @Override
    public Optional<Patient> getPatientCard(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM PersonalData p WHERE id = :id");
        query.setParameter("id", id);
        PersonalData personalData = (PersonalData)query.getResultList().get(0);
        String sql = "SELECT v FROM Visit v WHERE id =" + personalData.getId();
        List<Visit>visits = sessionFactory.getCurrentSession()
                .createQuery(sql, Visit.class)
                .getResultList();
        Query query1 = sessionFactory.getCurrentSession().createQuery(
                "SELECT j FROM JowlEntity j WHERE patient_id = :id");
        query1.setParameter("id", personalData.getId());
        JowlEntity jowlEntity = (JowlEntity) query1.getResultList().get(0);
        Patient patient = new Patient(personalData, jowlEntity,visits);
        return Optional.ofNullable(patient);
    }

    @Override
    public void changeSurname(Long idToSearch, String updatedSurname) {
        Query query = sessionFactory.getCurrentSession().createQuery("UPDATE PersonalData SET surname = :updatedSurname " +
                "WHERE id = :id");
        query.setParameter("updatedSurname", updatedSurname);
        query.setParameter("id", idToSearch);
        query.executeUpdate();
    }

    @Override
    public void changePhone(Long idToSearch, String updatedSurname) {
        Query query = sessionFactory.getCurrentSession().createQuery("UPDATE PersonalData SET phone = :updatedSurname " +
                "WHERE id = :id");
        query.setParameter("updatedSurname", updatedSurname);
        query.setParameter("id", idToSearch);
        query.executeUpdate();
    }

    @Override
    public boolean containsSpecificPersonalData(PersonalData personalData) {
        List<PersonalData> personalDataList = getPersonalData();
        return personalDataList.stream()
                .filter(personalData1 -> personalData1.equals(personalData))
                .findFirst().isPresent();
    }

    public void updateJowl(Long patientId, int toothNumber, ToothStatus toothStatus) {
        String sql = "UPDATE JowlEntity SET d"+ toothNumber + " = " + getToothStatusInt(toothStatus) +" WHERE patient_id = " + patientId;
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        query.executeUpdate();
    }

    private List<PersonalData> getPersonalData() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT p FROM PersonalData p", PersonalData.class)
                .getResultList();
    }

    private int getToothStatusInt(ToothStatus toothStatus) {
        switch (toothStatus) {
            case KARIES: return 0;
            case PLOMBA: return 1;
            case SAKNE: return 2;
            case KRONITIS: return 3;
            case KLAMERS: return 4;
            case NAV_ZOBA: return 5;
            case FASETE: return 6;
            case NONEMAMA_PROTEZE: return 7;
            case KRONITIS_AR_FAS: return 8;
            case PLAST_KRONITIS: return 9;
            case TILTINI: return 10;
            default: HEALTHY: return 11;
        }
    }
}
