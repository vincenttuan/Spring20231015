package com.psi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psi.model.vo.ProductSales;
import com.psi.service.ProductService;

@Controller
@RequestMapping("/chart")
public class ChartController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public String index(Model model) {
		List<ProductSales> productSales = productService.queryProductSales();
		model.addAttribute("productSales", productSales);
		return "chart"; 
	}
	
}