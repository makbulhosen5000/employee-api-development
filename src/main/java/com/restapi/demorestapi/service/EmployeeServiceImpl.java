package com.restapi.demorestapi.service;

import com.restapi.demorestapi.entity.Employee;
import com.restapi.demorestapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee saveReturnEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> findEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @Override
    public Optional<Employee> findEmployeeByName(String employeeName) {
        return employeeRepository.findByName(employeeName);
    }

    @Override
    public Optional<Employee> findEmployeeByPhone(String employeePhone) {
        return employeeRepository.findByPhone(employeePhone);
    }

    @Override
    public Optional<Employee> findEmployeeByEmail(String employeeEmail) {
        return employeeRepository.findByEmail(employeeEmail);
    }

    @Override
    public Optional<Employee> findEmployeeByDesignation(String employeeDesignNation) {
        return employeeRepository.findByDesignation(employeeDesignNation);
    }

    @Override
    public Boolean employeeNameExist(String employeeName) {
        return employeeRepository.findByName(employeeName).isPresent();
    }

    @Override
    public Long countEmployee() {
        return employeeRepository.count();
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public void deleteByEmployeeId(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public Employee saveAndReturnEmployee(Employee employee) {
        return null;
    }

    @Override
    public Page<Employee> findAllEmployees(Pageable pageable) {
        return null;
    }
}
