package dental_clinic.core.domain;

import javax.persistence.*;

@Entity
@Table(name="doctorsWorkGraphic")
public class WorkGraphic {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(name="monday_start")
    private String monday_start;

    @Column(name = "monday_end")
    private String monday_end;

    @Column(name="tuesday_start")
    private String tuesday_start;

    @Column(name = "tuesday_end")
    private String tuesday_end;

    @Column(name="wednesday_start")
    private String wednesday_start;

    @Column(name = "wednesday_end")
    private String wednesday_end;

    @Column(name="thursday_start")
    private String thursday_start;

    @Column(name = "thursday_end")
    private String thursday_end;

    @Column(name="friday_start")
    private String friday_start;

    @Column(name = "friday_end")
    private String friday_end;

    @Column(name="saturday_start")
    private String saturday_start;

    @Column(name = "saturday_end")
    private String saturday_end;

    @Column(name="sunday_start")
    private String sunday_start;

    @Column(name = "sunday_end")
    private String sunday_end;

    //String timesStart [] = new String [7];
    //String timesEnd [] = new String [7];


    public WorkGraphic() { }
    /*
    public WorkGraphic() {
        for (int i = 0; i < timesStart.length; i++) {
            timesStart[i] = "";
            timesEnd[i] = "";
        }
    }*/
/*
    public String[] getTimesStart() {
        return timesStart;
    }

    public String[] getTimesEnd() {
        return timesEnd;
    }

    public void setTimesStart(String[] timesStart) {
        this.timesStart = timesStart;
    }

    public void setTimesEnd(String[] timesEnd) {
        this.timesEnd = timesEnd;
    }

    @Override
    public String toString() {
        return "WorkGraphic:\n" +
                "monday: " + timesStart[0] + " - " + timesEnd[0] + "\n" +
                "tuesday: " + timesStart[1] + " - " + timesEnd[1] + "\n" +
                "wednesday: " + timesStart[2] + " - " + timesEnd[2] + "\n" +
                "thursdayStart: " + timesStart[3] + " - " + timesEnd[3] + "\n" +
                "fridayStart: " + timesStart[4] + " - " +  timesEnd[4] + "\n" +
                "saturdayStart: " + timesStart[5] + " - " + timesEnd[5] + "\n" +
                "sundayStart: " + timesStart[6] + " - " + timesEnd[6] + "\n";
    }*/
}
