
package com.employee.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "Projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int prId;
    private String title;
    private String technology;

//    @Accessors(chain = true)
//    @JsonIgnoreProperties(value = {"employees"}, ignoreUnknown = true)
    @JsonIgnore
    @ManyToMany(mappedBy = "project",cascade = CascadeType.PERSIST)
    List<Employee> employee;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return prId == project.prId && title.equals(project.title) && technology.equals(project.technology);
    }
    @Override
    public int hashCode() {
        return Objects.hash(prId, title, technology);
    }

    public Project(int prId, String title, String technology, List<Employee> employee) {
        this.prId = prId;
        this.title = title;
        this.technology = technology;
        this.employee = employee;
    }

    public Project() {
    }

    public int getPrId() {
        return prId;
    }

    public void setPrId(int prId) {
        this.prId = prId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + prId +
                ", title='" + title + '\'' +
                ", technology='" + technology;

    }
}