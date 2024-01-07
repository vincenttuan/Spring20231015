package com.psi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.psi.dto.DepartmentDTO;
import com.psi.dto.DepartmentPageDTO;
import com.psi.entity.Department;
import com.psi.repository.DepartmentRepository;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public DepartmentPageDTO findAllDepartments(Pageable pageable) {
		Page<Department> deptPage = departmentRepository.findAll(pageable);
		Page<DepartmentDTO> dtoPage = deptPage.map(this::convertToDTO);
		return new DepartmentPageDTO(dtoPage);
	}
	
	private DepartmentDTO convertToDTO(Department department) {
		DepartmentDTO dto = new DepartmentDTO();
		dto.setId(department.getId());
		dto.setName(department.getName());
		return dto;
	}
}
