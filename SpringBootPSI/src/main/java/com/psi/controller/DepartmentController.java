package com.psi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.psi.dto.DepartmentDto;
import com.psi.dto.DepartmentPageDto;
import com.psi.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/")
	// page=頁數&size=每頁筆數
	// 例如: page=0&size=5 第 1 頁每頁 5 筆
	// 例如: page=1&size=5 第 2 頁每頁 5 筆
	// 例如: page=2&size=5 第 3 頁每頁 5 筆
	public String index(@RequestParam(defaultValue = "0") int page, 
						@RequestParam(defaultValue = "10") int size,
						Model model, @ModelAttribute DepartmentDto departmentDto) {
		Pageable pageable = PageRequest.of(page, size);
		DepartmentPageDto departmentPageDto = departmentService.findAllDepartments(pageable); // 得到該分頁的數據實體
		model.addAttribute("departmentPageDto", departmentPageDto);
		return "department";
	}
	
	
	
}
