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
	
	// 修改
	public void update(EmployeeDto employeeDto, Long id) {
		// 根據 id 查找是否有此員工 ?
		Optional<Employee> employeeOpt = employeeRepository.findById(id);
		if(employeeOpt.isPresent()) {
			Employee employee = employeeOpt.get(); // 內含有 id 的員工資料
			employee.setName(employeeDto.getName()); // 更新員工姓名
			
			Long departmentId = employeeDto.getDepartment().getId();
			Optional<Department> departmentOpt = departmentRepository.findById(departmentId);
			if(departmentOpt.isPresent()) {
				employee.setDepartment(departmentOpt.get()); // 更新員工部門
				employeeRepository.save(employee); // 因為有員工 id 的存在, 所以 save 將會自動變成更新模式
			}
		}
	}
	
	// 刪除
	public void delete(Long id) {
		Optional<Employee> employeeOpt = employeeRepository.findById(id);
		if(employeeOpt.isPresent()) {
			employeeRepository.deleteById(id); // 根據 id 刪除
			//employeeRepository.delete(employeeOpt.get()); // 根據實體紀錄刪除
		}
	}
	
	// 查詢-單筆
	
	// 多筆-分頁
	
	// 多筆-全部
	
	
}
