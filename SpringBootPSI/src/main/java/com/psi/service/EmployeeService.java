package com.psi.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psi.model.dto.EmployeeDto;
import com.psi.model.po.Employee;
import com.psi.repository.DepartmentRepository;
import com.psi.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// 新增
	public void add(EmployeeDto employeeDto) {
		// dto 轉 po
		Employee employee = modelMapper.map(employeeDto, Employee.class);
		// 儲存
		employeeRepository.save(employee);
	}
	
}
