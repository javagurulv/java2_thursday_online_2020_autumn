package dental_clinic.core.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="personalData")
public class PersonalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "personalCode")
    private String personalCode;

    public PersonalData() { }

    public PersonalData(String name, String surname, String phone, String personalCode){
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.personalCode = personalCode;
    }

    public Long getId (){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public void setId (Long id){
        this.id = id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalData personalData = (PersonalData) o;
        return Objects.equals(personalCode, personalData.personalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalCode);
    }

    @Override
    public String toString() {
        return "\nPersonal data: " +
                "id: " + id +
                ", name: " + name +
                ", surname: " + surname +
                ", phone: " + phone +
                ", personalCode: " + personalCode + "\n";
    }
}
