package com.restapi.demorestapi.repository;

import com.restapi.demorestapi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT * FROM employees WHERE employee_name = ?1", nativeQuery = true)
    Optional<Employee> findByName(String name);
    @Query(value = "SELECT * FROM employees WHERE employee_phone = ?1", nativeQuery = true)
    Optional<Employee> findByPhone(String Phone);
    @Query(value = "SELECT * FROM employees WHERE employee_email = ?1", nativeQuery = true)
    Optional<Employee> findByEmail(String Email);
    @Query(value = "SELECT * FROM employees WHERE employee_designation = ?1", nativeQuery = true)
    Optional<Employee> findByDesignation(String Designation);


}
