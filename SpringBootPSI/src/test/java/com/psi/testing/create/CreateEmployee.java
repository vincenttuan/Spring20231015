package com.psi.testing.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.psi.entity.Department;
import com.psi.entity.Employee;
import com.psi.repository.DepartmentRepository;
import com.psi.repository.EmployeeRepository;

@SpringBootTest
public class CreateEmployee {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Test
	public void test() {
		Employee emp1 = new Employee();
		emp1.setName("張三");
		
		Employee emp2 = new Employee();
		emp2.setName("李四");
		
		Employee emp3 = new Employee();
		emp3.setName("王五");
		
		Employee emp4 = new Employee();
		emp4.setName("趙六");
		
		// 劉一, 陳二, 張三, 李四, 王五, 趙六, 孫七, 周八, 吳九, 鄭十
		
		// 配置與部門的關聯
		Department dep1 = departmentRepository.findById(1L).get();
		Department dep2 = departmentRepository.findById(2L).get();
		Department dep3 = departmentRepository.findById(3L).get();
		
		emp1.setDepartment(dep1);
		emp2.setDepartment(dep2);
		emp3.setDepartment(dep2);
		emp4.setDepartment(dep3);
		
		// 儲存
		employeeRepository.save(emp1);
		employeeRepository.save(emp2);
		employeeRepository.save(emp3);
		
	}
	
}
