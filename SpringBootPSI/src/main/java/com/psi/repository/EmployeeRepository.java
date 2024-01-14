package com.psi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psi.model.po.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
