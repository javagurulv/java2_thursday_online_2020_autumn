package dental_clinic.core.database.doctor;

import dental_clinic.core.domain.Doctor;
import dental_clinic.core.domain.WorkGraphic;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
/*
public class DoctorRowMapper implements RowMapper<Doctor> {


    @Override
    public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
        Doctor doctor = new Doctor();
        doctor.setId(rs.getLong("id"));
        doctor.setName(rs.getString("name"));
        doctor.setSurname(rs.getString("surname"));
        doctor.setPhone(rs.getString("phone"));
        doctor.setEmployed(rs.getBoolean("isEmployed"));
        WorkGraphic workGraphic = fillWorkGraphic(rs, rowNum);
        doctor.setWorkGraphic(workGraphic);
        return doctor;
    }

    private WorkGraphic fillWorkGraphic(ResultSet rs, int rowNum) throws SQLException{
        String [] timeStart = new String [7];
        String [] timeEnd = new String [7];
        timeStart[0] = rs.getString("monday_start");
        timeStart[1] = rs.getString("tuesday_start");
        timeStart[2] = rs.getString("wednesday_start");
        timeStart[3] = rs.getString("thursday_start");
        timeStart[4] = rs.getString("friday_start");
        timeStart[5] = rs.getString("saturday_start");
        timeStart[6] = rs.getString("sunday_start");

        timeEnd[0] = rs.getString("monday_end");
        timeEnd[1] = rs.getString("tuesday_end");
        timeEnd[2] = rs.getString("wednesday_end");
        timeEnd[3] = rs.getString("thursday_end");
        timeEnd[4] = rs.getString("friday_end");
        timeEnd[5] = rs.getString("saturday_end");
        timeEnd[6] = rs.getString("sunday_end");
        WorkGraphic workGraphic = new WorkGraphic();
        workGraphic.setTimesStart(timeStart);
        workGraphic.setTimesEnd(timeEnd);
        return workGraphic;
    }
}*/
