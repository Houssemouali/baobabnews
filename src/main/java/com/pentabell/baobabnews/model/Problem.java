package com.pentabell.baobabnews.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="problem")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name="type")
    private String type;

    @Column(name="description", columnDefinition="TEXT")
    private String description;

    @ManyToOne
    @JoinColumn (name="internauteId")
    private Internaute internautes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Problem(@NotNull String type, String description) {
        this.type = type;
        this.description = description;
    }

    public Problem() {
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Problem)) return false;
        Problem problem = (Problem) o;
        return getId() == problem.getId() &&
                Objects.equals(getType(), problem.getType()) &&
                Objects.equals(getDescription(), problem.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getDescription());
    }
}
