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
	
	// 模擬 Controller 的操作
	@Test
	public void test() {
		// 劉一, 陳二, 張三, 李四, 王五, 趙六, 孫七, 周八, 吳九, 鄭十
		/*
		DepartmentDto dep1 = departmentService.getDepartmentById(1L);
		EmployeeDto emp1 = new EmployeeDto();
		emp1.setName("劉一");
		emp1.setDepartment(dep1);
		
		// 儲存
		employeeService.add(emp1);
		*/
		
		EmployeeDto emp2 = new EmployeeDto();
		emp2.setName("陳二");
		
		// 儲存
		employeeService.add(emp2, 2L);
		
		System.out.println("Add OK!");
	}
	
}
