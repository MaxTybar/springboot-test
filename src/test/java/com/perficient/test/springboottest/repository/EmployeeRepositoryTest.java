package com.perficient.test.springboottest.repository;

import java.util.List;
import java.util.Optional;

import com.perficient.test.springboottest.entity.Employee;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    void saveEmployeeTest() {

        Employee employee = Employee.builder()
                .firstName("Max")
                .lastName("Tybar")
                .email("example@domain.com")
                .build();
        
        employeeRepository.save(employee);

        Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    void getEmployeeTest() {

        Employee employee = employeeRepository.findById(1L).get();

        Assertions.assertThat(employee.getId()).isEqualTo(1L);

    }

    @Test
    @Order(3)
    void getListOfEmployees() {

        List<Employee> employees = employeeRepository.findAll();

        Assertions.assertThat(employees.size()).isGreaterThan(0);
        
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    void updateEmployeeTest() {

        Employee employee = employeeRepository.findById(1L).get();

        employee.setEmail("updated@gmail.com");

        Employee updatedEmployee = employeeRepository.save(employee);

        Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo("updated@gmail.com");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    void deleteEmployeeTest() {

        Employee employee = employeeRepository.findById(1L).get();

        employeeRepository.delete(employee);
        
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail("updated@gmail.com");

        Assertions.assertThat(optionalEmployee).isEmpty();

    }

    
}
