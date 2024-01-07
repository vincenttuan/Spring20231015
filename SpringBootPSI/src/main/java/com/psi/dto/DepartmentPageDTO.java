package com.psi.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import com.psi.entity.Department;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentPageDTO {
	private List<DepartmentDTO> departments;
	private int currentPage;
	private int totalPage;
	
	public DepartmentPageDTO(Page<DepartmentDTO> deptPage) {
		this.departments = deptPage.getContent();
		this.currentPage = deptPage.getNumber();
		this.totalPage = deptPage.getTotalPages();
	}
}
