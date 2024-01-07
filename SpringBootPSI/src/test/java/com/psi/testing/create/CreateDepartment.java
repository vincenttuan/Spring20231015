package com.psi.testing.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.psi.entity.Department;
import com.psi.repository.DepartmentRepository;

@SpringBootTest
public class CreateDepartment {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Test
	public void test() {
		Department d1 = new Department();
		d1.setName("業務部");
		
		Department d2 = new Department();
		d2.setName("IT部");
		
		Department d3 = new Department();
		d3.setName("採購部");
		
		// 儲存
		departmentRepository.save(d1);
		departmentRepository.save(d2);
		departmentRepository.save(d3);
		
		System.out.println("Save OK!");
	}
	
}
