package com.perficient.test.springboottest.repository;

import java.util.Optional;

import com.perficient.test.springboottest.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    Optional<Employee> findByEmail(String email);
}
