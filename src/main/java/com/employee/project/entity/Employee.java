package com.employee.project.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int empId;
    private String name;
    private String city;

    @ManyToMany(cascade = CascadeType.PERSIST)

    @JoinTable(
            name="project_employee",
            joinColumns = @JoinColumn(
                    name="emp_Id",
                    referencedColumnName = "empId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name="pr_Id",
                    referencedColumnName = "prId"
            )
    )
    List <Project> project;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
         Employee employee = (Employee) o;
        return empId == employee.empId && name.equals(employee.name) && city.equals(employee.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, name, city);
    }

    public Employee(int empId, String name, String city, List <Project> project) {
        this.empId = empId;
        this.name = name;
        this.city = city;
        this.project = project;
    }

    public Employee() {

    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List <Project> getProject() {
        return project;
    }

    public void setProject(List<Project> project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + empId +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", project=" + project +
                '}';
    }
}
