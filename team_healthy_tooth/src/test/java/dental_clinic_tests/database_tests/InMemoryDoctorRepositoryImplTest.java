package dental_clinic_tests.database_tests;

import dental_clinic.core.domain.Doctor;
import dental_clinic.core.database.doctor.DoctorRepository;
import dental_clinic.core.database.doctor.InMemoryDoctorRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InMemoryDoctorRepositoryImplTest {

    DoctorRepository doctorRepository = new InMemoryDoctorRepositoryImpl();

    @Before
    public void init(){
        doctorRepository.addDoctor(new Doctor("Dok", "Dokis", "12345678"));
    }

    @Test
    public void testGetDoctorList(){
        assertTrue(doctorRepository.getDoctorList().size() == 4);
        assertTrue((doctorRepository.getDoctorList().get(3).getName().equals("Dok")));
    }

    @Test
    public void addDoctor() {
        doctorRepository.addDoctor(new Doctor("Name", "Surname", "12345678"));
        assertTrue(doctorRepository.getDoctorList().size() == 5);
        assertTrue((doctorRepository.getDoctorList().get(4).getName().equals("Name")));
        assertTrue(doctorRepository.getDoctorList().get(4).getId().equals(5L));
    }

    @Test
    public void testDeleteDoctorById() {
        doctorRepository.deleteDoctorById(4L);
        assertTrue(doctorRepository.getDoctorList().size() == 4);
        assertFalse(doctorRepository.getDoctorList().get(3).isEmployed());
    }

    @Test
    public void testContainsDoctor() {
        assertTrue(doctorRepository.containsDoctor(new Doctor("Dok", "Dokis", "12345678")));
        assertFalse(doctorRepository.containsDoctor(new Doctor("Dokis", "Dokiss", "12345678")));
    }

    @Test
    public void testContainsId() {
        assertTrue(doctorRepository.containsId(1L));
        assertFalse(doctorRepository.containsId(8L));
    }
}
