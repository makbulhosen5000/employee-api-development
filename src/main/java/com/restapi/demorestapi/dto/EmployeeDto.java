package com.restapi.demorestapi.dto;

import java.util.Objects;

public class EmployeeDto {
     private String employeeName;
     private String employeePhone;
     private String employeeEmail;
     private String employeeDesignNation;

     public EmployeeDto(){
         super();
     }
     public EmployeeDto(String employeeName,String employeePhone,String employeeEmail,String employeeDesignNation){
        this.employeeName=employeeName;
        this.employeePhone=employeePhone;
        this.employeeEmail=employeeEmail;
        this.employeeDesignNation=employeeDesignNation;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDto that = (EmployeeDto) o;
        return Objects.equals(employeeName, that.employeeName) && Objects.equals(employeePhone, that.employeePhone) && Objects.equals(employeeEmail, that.employeeEmail) && Objects.equals(employeeDesignNation, that.employeeDesignNation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeName, employeePhone, employeeEmail, employeeDesignNation);
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "employeeName='" + employeeName + '\'' +
                ", employeePhone='" + employeePhone + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeeDesignNation='" + employeeDesignNation + '\'' +
                '}';
    }
}
