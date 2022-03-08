package com.restapi.demorestapi.service;

import com.restapi.demorestapi.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EmployeeService {
    void saveEmployee(Employee employee);
    Employee saveReturnEmployee(Employee employee);

    Optional<Employee> findEmployeeById(Long employeeId);
    Optional<Employee> findEmployeeByName(String employeeName);
    Optional<Employee> findEmployeeByPhone(String employeePhone);
    Optional<Employee> findEmployeeByEmail(String employeeEmail);
    Optional<Employee> findEmployeeByDesignation(String employeeDesignNation);

    Boolean employeeNameExist(String employeeName);
    Long countEmployee();
    void delete(Employee employee);
    void deleteByEmployeeId(Long employeeId);

    Employee saveAndReturnEmployee(Employee employee);

    Page<Employee> findAllEmployees(Pageable pageable);
}
