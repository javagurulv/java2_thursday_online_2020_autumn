package dental_clinic.core.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="jowl")
public class Jowl {

    @Id
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="patient_id", nullable = false)
    private PersonalData personalData;

    @Column(name="d18", nullable = false)
    private String d18;

    @Column(name="d17", nullable = false)
    private String d17;

    @Column(name="d16", nullable = false)
    private String d16;

    @Column(name="d15", nullable = false)
    private String d15;

    @Column(name="d14", nullable = false)
    private String d14;

    @Column(name="d13", nullable = false)
    private String d13;

    @Column(name="d12", nullable = false)
    private String d12;

    @Column(name="d11", nullable = false)
    private String d11;

    @Column(name="d21", nullable = false)
    private String d21;

    @Column(name="d22", nullable = false)
    private String d22;

    @Column(name="d23", nullable = false)
    private String d23;

    @Column(name="d24", nullable = false)
    private String d24;

    @Column(name="d25", nullable = false)
    private String d25;

    @Column(name="d26", nullable = false)
    private String d26;

    @Column(name="d27", nullable = false)
    private String d27;

    @Column(name="d28", nullable = false)
    private String d28;

    @Column(name="d55", nullable = false)
    private String d55;

    @Column(name="d54", nullable = false)
    private String d54;

    @Column(name="d53", nullable = false)
    private String d53;

    @Column(name="d52", nullable = false)
    private String d52;

    @Column(name="d51", nullable = false)
    private String d51;

    @Column(name="d61", nullable = false)
    private String d61;

    @Column(name="d62", nullable = false)
    private String d62;

    @Column(name="d63", nullable = false)
    private String d63;

    @Column(name="d64", nullable = false)
    private String d64;

    @Column(name="d65", nullable = false)
    private String d65;

    @Column(name="d48", nullable = false)
    private String d48;

    @Column(name="d47", nullable = false)
    private String d47;

    @Column(name="d46", nullable = false)
    private String d46;

    @Column(name="d45", nullable = false)
    private String d45;

    @Column(name="d44", nullable = false)
    private String d44;

    @Column(name="d43", nullable = false)
    private String d43;

    @Column(name="d42", nullable = false)
    private String d42;

    @Column(name="d41", nullable = false)
    private String d41;

    @Column(name="d31", nullable = false)
    private String d31;

    @Column(name="d32", nullable = false)
    private String d32;

    @Column(name="d33", nullable = false)
    private String d33;

    @Column(name="d34", nullable = false)
    private String d34;

    @Column(name="d35", nullable = false)
    private String d35;

    @Column(name="d36", nullable = false)
    private String d36;

    @Column(name="d37", nullable = false)
    private String d37;

    @Column(name="d38", nullable = false)
    private String d38;

    @Column(name="d85", nullable = false)
    private String d85;

    @Column(name="d84", nullable = false)
    private String d84;

    @Column(name="d83", nullable = false)
    private String d83;

    @Column(name="d82", nullable = false)
    private String d82;

    @Column(name="d81", nullable = false)
    private String d81;

    @Column(name="d71", nullable = false)
    private String d71;

    @Column(name="d72", nullable = false)
    private String d72;

    @Column(name="d73", nullable = false)
    private String d73;

    @Column(name="d74", nullable = false)
    private String d74;

    @Column(name="d75", nullable = false)
    private String d75;

    //private Map<Integer, List<ToothStatus>> jowl;

    /*public Jowl (){
        jowl = createNewJowl();
    }*/

    public Jowl() { }
/*
    public Map<Integer, List<ToothStatus>> getJowl() {
        return jowl;
    }

    private Map <Integer, List<ToothStatus>> createNewJowl(){
        Map <Integer, List<ToothStatus>> newJowl = new HashMap<>();
        for (int i = 18; i>10; i--) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }
        for (int i = 21; i<29; i++) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }
        for (int i = 55; i>50; i--) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }
        for (int i = 61; i<66; i++) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }

        for (int i = 48; i>40; i--) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }
        for (int i = 31; i<39; i++) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }
        for (int i = 85; i>80; i--) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }
        for (int i = 71; i<76; i++) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }
        return newJowl;
    }

    public void setJowl(Map<Integer, List<ToothStatus>> jowl) {
        this.jowl = jowl;
    }

    public boolean updateJowl(int toothNumber, ToothStatus toothStatus) {
        if (jowl.containsKey(toothNumber)) {
            jowl.get(toothNumber).add(toothStatus);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        String result = "Jowl{\n";
        for (Integer key : jowl.keySet()){
            result += key + " " + jowl.get(key) + "\n";
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jowl jowl1 = (Jowl) o;
        return Objects.equals(jowl, jowl1.jowl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jowl);
    }*/
}
