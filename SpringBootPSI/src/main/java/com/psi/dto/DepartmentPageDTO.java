package com.psi.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentPageDTO {
	private List<DepartmentDTO> departments;
	private int currentPage;
	private int totalPage;
	
	public DepartmentPageDTO(Page<DepartmentDTO> page) {
		this.departments = page.getContent();
		this.currentPage = page.getNumber();
		this.totalPage = page.getTotalPages();
	}
}
