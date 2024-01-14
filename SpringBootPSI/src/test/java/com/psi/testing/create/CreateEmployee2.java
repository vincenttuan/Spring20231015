package com.psi.testing.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.psi.model.dto.DepartmentDto;
import com.psi.model.dto.EmployeeDto;
import com.psi.model.po.Department;
import com.psi.model.po.Employee;
import com.psi.repository.DepartmentRepository;
import com.psi.repository.EmployeeRepository;
import com.psi.service.DepartmentService;
import com.psi.service.EmployeeService;

@SpringBootTest
public class CreateEmployee2 {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Test
	public void test() {
		// 劉一, 陳二, 張三, 李四, 王五, 趙六, 孫七, 周八, 吳九, 鄭十
		
		DepartmentDto dep1 = departmentService.getDepartmentById(1L);
		EmployeeDto emp1 = new EmployeeDto();
		emp1.setName("劉一");
		emp1.setDepartment(dep1);
		
		// 儲存
		employeeRepository.save(emp1);
		employeeRepository.save(emp2);
		employeeRepository.save(emp3);
		employeeRepository.save(emp4);
	}
	
}
