package dental_clinic.core.database.doctor;

import dental_clinic.core.domain.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
/*
//@Component
public class JdbcDoctorRepositoryImpl implements DoctorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Doctor> getDoctorList() {
        String sql = "SELECT * FROM doctor";
        return jdbcTemplate.query(sql, new DoctorRowMapper());
    }

    @Override
    public void addDoctor(Doctor doctor) {
        jdbcTemplate.update(
                "INSERT INTO doctor (name, surname, phone, isEmployed) "
                + "VALUES (?, ?, ?, ?)",
                doctor.getName(), doctor.getSurname(), doctor.getPhone(), true
        );
    }

    @Override
    public boolean deleteDoctorById(Long id) {
        String sql = "DELETE FROM doctor WHERE id = ?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public boolean containsDoctor(Doctor doctor) {
        String sql = "SELECT * FROM doctor WHERE name = \"" + doctor.getName() +
                "\" AND surname = \"" + doctor.getSurname() + "\";";
        return jdbcTemplate.query(sql, new DoctorRowMapper()).size() == 1;
    }

    @Override
    public boolean containsId(Long id) {
        String sql = "SELECT * FROM doctor WHERE id = " + id + ";";
        return jdbcTemplate.query(sql, new DoctorRowMapper()).size() == 1;
    }

    @Override
    public boolean specificDoctorIsEmployed(Doctor doctor) {
        String sql = "SELECT * FROM doctor WHERE name = \"" + doctor.getName() +
                "\" AND surname = \"" + doctor.getSurname() + "\";";
        return jdbcTemplate.query(sql, new DoctorRowMapper()).get(0).isEmployed();
    }

    @Override
    public Optional<Doctor> getDoctorById(Long id) {
        try {
            Doctor doctor = jdbcTemplate.queryForObject("SELECT * FROM doctor WHERE id = ?",
                    new Object[]{id}, new BeanPropertyRowMapper<>(Doctor.class));
            return Optional.of(doctor);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateWorkGraphicForSpecificDate(Long id, Integer day, String timeFrom, String timeTo) {
        jdbcTemplate.update(
                "UPDATE doctor " +
                    "SET " + getDayNameStart(day) + " = \"" + timeFrom +
                    "\" WHERE id = " + id + ";");
        jdbcTemplate.update(
                "UPDATE doctor " +
                        "SET " + getDayNameEnd(day) + " = \"" + timeTo +
                        "\" WHERE id = " + id + ";");
    }

    private String getDayNameStart(int day) {
        switch (day) {
            case 1: return "monday_start";
            case 2: return "tuesday_start";
            case 3: return "wednesday_start";
            case 4: return "thursday_start";
            case 5: return "friday_start";
            case 6: return "saturday_start";
            default: return "sunday_start";
        }
    }

    private String getDayNameEnd(int day) {
        switch (day) {
            case 1: return "monday_end";
            case 2: return "tuesday_end";
            case 3: return "wednesday_end";
            case 4: return "thursday_end";
            case 5: return "friday_end";
            case 6: return "saturday_end";
            default: return "sunday_end";
        }
    }
}*/
