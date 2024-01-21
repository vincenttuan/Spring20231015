package com.psi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.psi.model.dto.DepartmentDto;
import com.psi.model.dto.DepartmentPageDto;
import com.psi.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	/*
	@GetMapping("/") // 分頁版
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
	*/
	
	@GetMapping("/") // 不分頁版
	public String index(@ModelAttribute DepartmentDto departmentDto, Model model) {
		List<DepartmentDto> departmentDtos = departmentService.findAll();
		model.addAttribute("departmentDtos", departmentDtos);
		model.addAttribute("departmentDto", departmentDto);
		return "department";
	}
	
	// 取得單筆
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		DepartmentDto departmentDto = departmentService.getDepartmentDtoById(id);
		model.addAttribute("departmentDto", departmentDto);
		return "department-edit";
	}
	
	// 新增
	@PostMapping("/")
	public String add(DepartmentDto departmentDto) {
		departmentService.add(departmentDto);
		// 新增完畢之後直接重導到首頁
		return "redirect:/department/";
	}
	
	// 修改
	@PutMapping("/{id}")
	public String update(@PathVariable("id") Long id, DepartmentDto departmentDto) {
		departmentService.update(departmentDto, id);
		// 修改完畢之後直接重導到首頁
		return "redirect:/department/";
	}
	
	// 刪除
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		departmentService.delete(id);
		// 刪除完畢之後直接重導到首頁
		return "redirect:/department/";
	}
	
	
}
