package com.capg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
