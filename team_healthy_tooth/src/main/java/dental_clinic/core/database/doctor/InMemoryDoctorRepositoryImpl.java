package dental_clinic.core.database.doctor;

import dental_clinic.core.domain.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/*
//@Component
public class InMemoryDoctorRepositoryImpl implements DoctorRepository {

    private List<Doctor> doctors = addSomeDoctors();
    private Long id = 4L;

    @Override
    public List<Doctor> getDoctorList() {
        return doctors;
    }

    @Override
    public void addDoctor(Doctor doctor) {
        doctor.setId(id);
        id++;
        doctors.add(doctor);
    }

    @Override
    public boolean deleteDoctorById(Long id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId().equals(id)) {
                doctor.setEmployed(false);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsDoctor(Doctor doctor) {
        return doctors.stream()
            .anyMatch(doctor1 -> doctor1.equals(doctor));
    }

    @Override
    public boolean containsId(Long id) {
        return doctors.stream()
                .anyMatch(doctor -> doctor.getId().equals(id));
    }

    @Override
    public boolean specificDoctorIsEmployed(Doctor doctor) {
        return doctors.stream()
                .filter(doctor1 -> doctor1.getName().equals(doctor.getName())
                && doctor1.getSurname().equals(doctor.getSurname())
                && doctor1.isEmployed())
                .findAny().isPresent();
    }

    @Override
    public Optional<Doctor> getDoctorById(Long id) {
        return doctors.stream()
                .filter(doctor -> doctor.getId().equals(id))
                .findAny();
    }

    @Override
    public void updateWorkGraphicForSpecificDate(Long id, Integer day, String timeFrom, String timeTo) {
        for (Doctor doctor : doctors) {
            if (doctor.getId().equals(id)) {
                doctor.getWorkGraphic().getTimesStart()[day-1] = timeFrom;
                doctor.getWorkGraphic().getTimesEnd()[day-1] = timeTo;
                break;
            }
        }
    }

    private List<Doctor> addSomeDoctors(){
        List<Doctor> doctors = new ArrayList<>();
        Doctor doctor1 = new Doctor("Doctor", "Zlo", "12345678");
        doctor1.setId(1L);
        String workStarts [] = {"08:00", "", "10:00", "12:00", "14:00", "09:00", "11:00"};
        String workEnds [] = {"18:00", "", "15:00", "16:00", "19:00", "19:00", "13:00"};
        doctor1.getWorkGraphic().setTimesStart(workStarts);
        doctor1.getWorkGraphic().setTimesEnd(workEnds);
        doctors.add(doctor1);
        Doctor doctor2 = new Doctor("Doctor", "Haos", "98765432");
        doctor2.setId(2L);
        doctors.add(doctor2);
        Doctor doctor3 = new Doctor("Doc", "Incognito", "65478921");
        doctor3.setId(3L);
        doctors.add(doctor3);
        return doctors;
    }


}*/
