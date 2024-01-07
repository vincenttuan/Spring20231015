package com.psi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.psi.dto.DepartmentPageDTO;
import com.psi.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/")
	@ResponseBody
	public String index(@RequestParam(defaultValue = "0") int page, 
						@RequestParam(defaultValue = "0") int size,
						Model model) {
		Pageable pageable = PageRequest.of(page, size);
		DepartmentPageDTO departmentPageDTO = departmentService.findAllDepartments(pageable);
		return departmentPageDTO + "";
	}
	
}
