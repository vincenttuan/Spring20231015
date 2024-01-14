package com.psi.testing.read;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.psi.dto.DepartmentPageDto;
import com.psi.service.DepartmentService;

@SpringBootTest
public class ReadDepartment {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Test
	public void test() {
		Pageable pageable = PageRequest.of(0, 10);
		DepartmentPageDto dto = departmentService.findAllDepartments(pageable);
		dto.getDepartments().forEach(dept -> System.out.println(dept.getName()));
		System.out.println(dto.getCurrentPage());
		System.out.println(dto.getTotalPage());
	}
	
}
