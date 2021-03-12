package java2.application_target_list.core.domain;

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name="targets")
public class Target {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="target_name", nullable = false)
    private String name;

    @Column(name="target_description", nullable = false)
    private String description;

    @Column(name="target_deadline", nullable = false)
    private Long deadline;

    public Target() {
    }

    public Target(String name, String description, Long deadline) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setDeadline(Long deadline) {
        this.deadline = deadline;
    }

    public Long getDeadline() {
        return deadline;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Target target = (Target) o;
        return deadline.equals(target.deadline) &&
                Objects.equals(name, target.name) &&
                Objects.equals(description, target.description) &&
                Objects.equals(id, target.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, id, deadline);
    }
}
