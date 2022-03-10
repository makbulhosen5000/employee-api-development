package com.restapi.demorestapi.controller;

import com.restapi.demorestapi.dto.EmployeeDto;
import com.restapi.demorestapi.entity.Employee;
import com.restapi.demorestapi.exception.ConflictException;
import com.restapi.demorestapi.exception.InternalServerErrorException;
import com.restapi.demorestapi.exception.NotFoundException;
import com.restapi.demorestapi.exception.UnprocessableEntityException;
import com.restapi.demorestapi.helper.EntityState;
import com.restapi.demorestapi.helper.Helper;
import com.restapi.demorestapi.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/save/")
    public ResponseEntity<Employee> storeEmployee(@RequestBody EmployeeDto dto) {
        if (dto.getEmployeeName() != null && dto.getEmployeePhone() != null && dto.getEmployeeEmail() != dto.getEmployeeDesignNation()) {
            if (dto.getEmployeeName().isBlank() || dto.getEmployeePhone().isBlank() || dto.getEmployeeEmail().isBlank() || dto.getEmployeeDesignNation().isBlank()) {
                throw new UnprocessableEntityException("Invalid employee name  and employee phone and employee email,employee desingnation");
            }
            if (dto.getEmployeeName().length() > 15 || dto.getEmployeePhone().length() > 15 || dto.getEmployeeEmail().length() > 50 || dto.getEmployeeDesignNation().length() > 30) {
                throw new UnprocessableEntityException("name should not be more than 15 chars and  phone should not be more than 15 chars and email should not more that 50 chars,designation should not more than 30 chars");
            }
            if (employeeService.employeeNameExist(dto.getEmployeeName())) {
                throw new ConflictException("Employee Name already exists.");
            }
            if (employeeService.employeeNameExist(dto.getEmployeePhone())) {
                throw new ConflictException("Employee phone already exists.");
            }
            if (employeeService.employeeNameExist(dto.getEmployeeEmail())) {
                throw new ConflictException("Employee email already exists.");
            }
            if (employeeService.employeeNameExist(dto.getEmployeeDesignNation())) {
                throw new ConflictException("Employee designNation already exists.");
            }

            Employee theEmployee;
            Employee employee = new Employee();
            employee.setEmployeeName(dto.getEmployeeName());
            employee.setEmployeePhone(dto.getEmployeePhone());
            employee.setEmployeePhone(dto.getEmployeePhone());
            employee.setEmployeeDesignNation(dto.getEmployeeDesignNation());
            employee.setEntityState(EntityState.Incomplete.toString());
            employee.setCreatedAt(Helper.getCurrentTimestamp());
            employee.setUpdatedAt(Helper.getCurrentTimestamp());
            try {
                theEmployee = employeeService.saveReturnEmployee(employee);
            } catch (Exception e) {
                logger.error("Could not save new employee. ERROR: " + e.getMessage());
                throw new InternalServerErrorException("Something went wrong on the server!");
            }

            return new ResponseEntity<>(theEmployee, HttpStatus.CREATED);
        }else {
                throw new UnprocessableEntityException("Employee name and phone,email  and employee designation fields are required!");
            }

        }

    @PutMapping("/update/by/id/{employeeId}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable("employeeId") Long employeeId, @RequestBody EmployeeDto dto){
        Optional<Employee> optionalEmployee = employeeService.findEmployeeById(employeeId);
        if (optionalEmployee.isPresent()) {
            Employee theEmployee;
            Employee employee = optionalEmployee.get();

            if (dto.getEmployeeName() != null && !dto.getEmployeeName().isBlank()) {
                employee.setEmployeeName(dto.getEmployeeName());

            }
            if (dto.getEmployeePhone() != null && !dto.getEmployeePhone().isBlank()) {
                employee.setEmployeePhone(dto.getEmployeePhone());

            }
            if (dto.getEmployeeEmail() != null && !dto.getEmployeeEmail().isBlank()) {
                employee.setEmployeeEmail(dto.getEmployeeEmail());
            }
            if (dto.getEmployeeDesignNation() != null && !dto.getEmployeeDesignNation().isBlank()) {
                employee.setEmployeeDesignNation(dto.getEmployeeDesignNation());
            }
           employee.setUpdatedAt(Helper.getCurrentTimestamp());

            try {
                theEmployee = employeeService.saveReturnEmployee(employee);
            } catch (Exception e) {
                logger.error("Could not update employee. ERROR: " + e.getMessage());
                throw new InternalServerErrorException("Something went wrong on the server!");
            }

            return new ResponseEntity<>(theEmployee, HttpStatus.ACCEPTED); // 202

        } else {
            throw new NotFoundException("No employee found by this Id.");
        }
    }
    @DeleteMapping("/delete/by/id/{employeeId}")
    public ResponseEntity<Void> deleteEmployeById(@PathVariable("employeeId") Long employeeId) {

        Optional<Employee> optionalEmployee = employeeService.findEmployeeById(employeeId);

        if (optionalEmployee.isPresent()) {

            Employee employee = optionalEmployee.get();

            try {
                employeeService.delete(employee);
            } catch (Exception e) {
                logger.error("Could not delete employee. ERROR: " + e.getMessage());
                throw new InternalServerErrorException("Something went wrong on the server!");
            }

            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204

        } else {
            throw new NotFoundException("No task found by this Id.");
        }

    }
    @GetMapping("/find/by/id/{emloyeeId}")
    public ResponseEntity<Employee> findEmployeById(@PathVariable("emloyeeId") Long emloyeeId) {

        Optional<Employee> optionalEmployee = employeeService.findEmployeeById(emloyeeId);

        if (optionalEmployee.isPresent()) {

            Employee employee = optionalEmployee.get();
            return new ResponseEntity<>(employee, HttpStatus.OK); // 200

        } else {
            throw new NotFoundException("No employee found by this Id.");
        }

    }
    @GetMapping("/find/all/")
    public ResponseEntity<List<Employee>> findAllEmployees(@PageableDefault(size = 15) Pageable pageable) {

        Page<Employee> contents = employeeService.findAllEmployees(pageable);

        if (contents.getContent().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            long totalContents = contents.getTotalElements();
            int nbPageContents = contents.getNumberOfElements();

            HttpHeaders headers = new HttpHeaders();
            headers.add("X-Total-Count", String.valueOf(totalContents));

            if (nbPageContents < totalContents) {
                headers.add("first", Helper.buildPageUri(PageRequest.of(0, contents.getSize())));
                headers.add("last", Helper.buildPageUri(PageRequest.of(contents.getTotalPages() - 1, contents.getSize())));

                if (contents.hasNext()) {
                    headers.add("next", Helper.buildPageUri(contents.nextPageable()));
                }

                if (contents.hasPrevious()) {
                    headers.add("prev", Helper.buildPageUri(contents.previousPageable()));
                }

                return new ResponseEntity<>(contents.getContent(), headers, HttpStatus.PARTIAL_CONTENT); // 206
            } else {
                return new ResponseEntity<>(contents.getContent(), headers, HttpStatus.OK); // 200
            }
        }

    }
}
