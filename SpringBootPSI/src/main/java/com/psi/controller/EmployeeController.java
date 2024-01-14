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
import com.psi.model.dto.EmployeeDto;
import com.psi.model.dto.EmployeePageDto;
import com.psi.service.DepartmentService;
import com.psi.service.EmployeeService;

/*
 * URL 路徑設計
 * GET  /employee/?page=0&size=5 分頁查詢
 * GET  /employee/edit/{id} 單筆查詢(修改用)
 * POST /employee/ 新增
 * PUT  /employee/{id} 修改
 * GET  /employee/delete/{id} 刪除
 * */

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/")
	public String index(@RequestParam(name = "page", defaultValue = "0") int page,
						@RequestParam(name = "size", defaultValue = "5") int size,
						@ModelAttribute EmployeeDto employeeDto, Model model) {
		
		Pageable pageable = PageRequest.of(page, size);
		List<DepartmentDto> departmentDtos = departmentService.findAll();
		EmployeePageDto employeePageDto = employeeService.findAll(pageable);
		model.addAttribute("departmentDtos", departmentDtos);
		model.addAttribute("employeePageDto", employeePageDto);
		//model.addAttribute("employeeDto", employeeDto);
		return "employee";
	}
	
	@PostMapping("/")
	public String create(EmployeeDto employeeDto) {
		employeeService.add(employeeDto);
		return "redirect:/employee/";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		EmployeeDto employeeDto = employeeService.getEmployeeById(id);
		model.addAttribute("employeeDto", employeeDto);
		model.addAttribute("departmentDtos", departmentService.findAll());
		return "employee-edit";
	}
	
	@PutMapping("/{id}")
	public String update(EmployeeDto employeeDto, @PathVariable("id") Long id) {
		employeeService.update(employeeDto, id);
		return "redirect:/employee/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		employeeService.delete(id);
		return "redirect:/employee/";
	}
}








