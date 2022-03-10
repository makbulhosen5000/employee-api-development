package com.restapi.demorestapi.entity;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 25)
    @Column(name = "employee_name")
    private  String employeeName;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 20)
    @Column(name = "employee_phone")
    private String employeePhone;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Column(name = "employee_email")
    private String employeeEmail;

    @NotNull
    @NotBlank
    @Size(min = 3, max =60)
    @Column(name = "employee_designation")
    private  String employeeDesignNation;

    @Nullable
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Nullable
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public Employee(){

    }

    public Employee(Long employeeId, String employeeName, String employeePhone, String employeeEmail, String employeeDesignNation, @Nullable Timestamp createdAt, @Nullable Timestamp updatedAt) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeePhone = employeePhone;
        this.employeeEmail = employeeEmail;
        this.employeeDesignNation = employeeDesignNation;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeDesignNation() {
        return employeeDesignNation;
    }

    public void setEmployeeDesignNation(String employeeDesignNation) {
        this.employeeDesignNation = employeeDesignNation;
    }

    @Nullable
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@Nullable Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Nullable
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(@Nullable Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId.equals(employee.employeeId) && employeeName.equals(employee.employeeName) && employeePhone.equals(employee.employeePhone) && employeeEmail.equals(employee.employeeEmail) && employeeDesignNation.equals(employee.employeeDesignNation) && createdAt.equals(employee.createdAt) && updatedAt.equals(employee.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, employeeName, employeePhone, employeeEmail, employeeDesignNation, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", employeePhone='" + employeePhone + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeeDesignNation='" + employeeDesignNation + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public void setEntityState(String toString) {
    }
}
