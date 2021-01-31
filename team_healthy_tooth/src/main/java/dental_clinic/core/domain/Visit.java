package dental_clinic.core.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name="visit")
public class Visit {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="patient_id")
    private PersonalData personalData;

    //private Long patientsId;

    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    //private List<Manipulation> manipulations;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name="dateAndTime")
    private Date date;

    @Column(name="tooth_number")
    private Integer toothNumber;

    @Column(name="tooth_status")
    private String toothStatus;

    @Column(name="sum")
    private Integer calculateSum; //TODO

    @Column(name="remarks")
    private String remarks;
    //private Optional<String> comment;

    public Visit() { }

    public Visit (Long id, Integer toothNumber, Optional<String> comment, ToothStatus toothStatus,
                  Doctor doctor, List<Manipulation> manipulations, Date date){
        //this.patientsId = id;
        this.toothNumber = toothNumber;
        //this.comment = comment;
        //this.toothStatus = toothStatus;
        this.doctor = doctor;
        //this.manipulations = manipulations;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
/*
    public Long getPatientsId() {
        return patientsId;
    }*/

    public Integer getToothNumber() {
        return toothNumber;
    }
/*
    public Optional<String> getComment() {
        return comment;
    }*/

    public String getToothStatus() {
        return "healthy";
                //toothStatus;
    }

    public Doctor getDoctor() {
        return doctor;
    }
/*
    public List<Manipulation> getManipulations() {
        return manipulations;
    }*/

    public Date getDate() {
        return  date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(toothNumber, visit.toothNumber) &&
                toothStatus == visit.toothStatus &&
                Objects.equals(doctor, visit.doctor) &&
                Objects.equals(date, visit.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toothNumber, toothStatus, doctor, date);
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return "\nVisit: " +
                //"Patient's id: " + patientsId +
                ", Tooth number: " + toothNumber +
                //", Comment: " + comment +
                ", Tooth status: " + toothStatus +
                ", Dr." + doctor.getSurname() +
                ", Date: " + simpleDateFormat.format(date);
    }
}