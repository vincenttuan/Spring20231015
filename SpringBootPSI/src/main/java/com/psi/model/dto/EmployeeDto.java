package com.psi.model.dto;

import com.psi.model.po.Department;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
	
	private Long id; // 員工序號
	private String name; // 員工姓名
	private Department department; // 所屬部門
	
}
