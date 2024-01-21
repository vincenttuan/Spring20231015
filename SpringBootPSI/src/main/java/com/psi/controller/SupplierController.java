package com.psi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psi.model.dto.SupplierDto;
import com.psi.service.SupplierService;

/**
 * WEB API:
 * --------------------------------------------------------------
 * Method   Path                    功能
 * --------------------------------------------------------------
 * GET      "/supplier/"            首頁 
 * GET      "/supplier/edit/{id}"   取得單筆, 給修改頁面使用
 * GET      "/supplier/delete/{id}" 根據 id 來刪除資料
 * POST     "/supplier/"            新增
 * PUT      "/supplier/{id}"        修改
 * --------------------------------------------------------------
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	
	@GetMapping("/")
	public String index(@ModelAttribute SupplierDto supplierDto, Model model) {
		List<SupplierDto> supplierDtos = supplierService.findAll();
		model.addAttribute("supplierDtos", supplierDtos);
		model.addAttribute("supplierDto", supplierDto); // 可以不用撰寫, 因為有設定 @ModelAttribute SupplierDto supplierDto
		return "supplier";
	}
	
}









