package com.psi.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psi.model.dto.EmployeeDto;
import com.psi.model.po.Department;
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
	
	// 新增 I
	public void add(EmployeeDto employeeDto) {
		// dto 轉 po
		Employee employee = modelMapper.map(employeeDto, Employee.class);
		// 儲存
		employeeRepository.save(employee);
	}
	
	// 新增 II
	public void add(EmployeeDto employeeDto, Long departmentId) {
		// dto 轉 po
		Employee employee = modelMapper.map(employeeDto, Employee.class);
		if(employee.getDepartment() == null) {
			// 根據 departmentId 來查找 department 物件
			Optional<Department> departmentOpt = departmentRepository.findById(departmentId);
			if(departmentOpt.isPresent()) {
				employee.setDepartment(departmentOpt.get()); // 配置指定部門
				employeeRepository.save(employee);
			}
		}
	}
	
	
}
