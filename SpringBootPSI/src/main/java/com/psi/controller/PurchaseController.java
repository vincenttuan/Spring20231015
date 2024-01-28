package com.psi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psi.model.dto.EmployeeDto;
import com.psi.model.dto.ProductDto;
import com.psi.model.dto.PurchaseDto;
import com.psi.model.dto.PurchaseItemDto;
import com.psi.model.dto.SupplierDto;
import com.psi.service.EmployeeService;
import com.psi.service.ProductService;
import com.psi.service.PurchaseService;
import com.psi.service.SupplierService;

/**
 * WEB API:
 * ----------------------------------------------------------------------
 * Method   Path                     功能
 * ----------------------------------------------------------------------
 * GET      "/purchase/"             採購單主檔-首頁 
 * POST     "/purchase/"             採購單主檔-新增
 * GET      "/purchase/edit/{pid}    採購單主檔-取得單筆(修改畫面用)
 * PUT      "/purchase/{pid}"        採購單主檔-修改
 * GET      "/purchase/delete/{pid}" 採購單主檔-刪除
 * ----------------------------------------------------------------------
 * GET      "/purchase/{pid}/item"              採購單明細-檢視指定的採購單主檔明細
 * POST     "/purchase/{pid}/item"              採購單明細-新增
 * GET      "/purchase/edit/{pid}/item/{iid}"   採購單明細-取得單筆(修改畫面用)
 * PUT      "/purchase/{pid}/item/{iid}"        採購單明細-修改
 * GET      "/purchase/delete/{pid}/item/{iid}" 採購單明細-刪除
 * ----------------------------------------------------------------------
 */
@Controller
@RequestMapping("/purchase")
public class PurchaseController {
	
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ProductService productService;
	
	// 採購單主檔-首頁
	@GetMapping("/")
	public String index(Model model) {
		PurchaseDto purchaseDto = new PurchaseDto();
		List<PurchaseDto> purchaseDtos = purchaseService.findAll();
		
		List<SupplierDto> supplierDtos = supplierService.findAll();
		List<EmployeeDto> employeeDtos = employeeService.findAll();
		
		model.addAttribute("purchaseDto", purchaseDto);
		model.addAttribute("purchaseDtos", purchaseDtos);
		model.addAttribute("supplierDtos", supplierDtos);
		model.addAttribute("employeeDtos", employeeDtos);
		return "purchase";
	}
	
	// 採購單主檔-新增
	@PostMapping("/")
	public String create(PurchaseDto purchaseDao) {
		purchaseService.add(purchaseDao);
		return "redirect:/purchase/";
	}
	
	// 採購單主檔-取得單筆(修改畫面用)
	@GetMapping("/edit/{pid}") 
	public String edit(@PathVariable("pid") Long pid, Model model) {
		PurchaseDto purchaseDto = purchaseService.getPurchaseDtoById(pid);
		
		List<SupplierDto> supplierDtos = supplierService.findAll();
		List<EmployeeDto> employeeDtos = employeeService.findAll();
		
		model.addAttribute("purchaseDto", purchaseDto);
		model.addAttribute("supplierDtos", supplierDtos);
		model.addAttribute("employeeDtos", employeeDtos);
		return "purchase-edit";
	}
	
	// 採購單主檔-修改
	@PutMapping("/{pid}") 
	public String update(@PathVariable("pid") Long pid, PurchaseDto purchaseDto) {
		purchaseService.update(purchaseDto, pid);
		return "redirect:/purchase/";
	}
	
	// 採購單主檔-刪除
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		purchaseService.delete(id);
		return "redirect:/purchase/";
	}
	
	
	// 採購單明細-檢視指定的採購單主檔明細
	// pid -> 採購單主檔 id
	@GetMapping("/{pid}/item")
	public String getPurchaseItem(Model model, @PathVariable("pid") Long pid) {
		PurchaseDto purchaseDto = purchaseService.getPurchaseDtoById(pid);
		PurchaseItemDto purchaseItemDto = new PurchaseItemDto();
		List<ProductDto> productDtos = productService.findAll();
		model.addAttribute("purchaseDto", purchaseDto);
		model.addAttribute("purchaseItemDto", purchaseItemDto);
		model.addAttribute("productDtos", productDtos);
		return "purchase-item";
	}
	
	// 採購單明細-新增
	// 新增訂單項目, pid: 訂單主檔 id
	@PostMapping("/{pid}/item")
	public String addPurchaseItem(PurchaseItemDto purchaseItemDto, @PathVariable("pid") Long pid) {
		// 將訂單項目加入到指定 pid 的訂單主檔
		purchaseService.addPurchaseItem(purchaseItemDto, pid);
		return "redirect:/purchase/" + pid + "/item";
	}
	
	// 採購單明細-取得單筆(修改畫面用)
	@GetMapping("/edit/{pid}/item/{iid}")
	// pid: 訂單主檔 id
	// iid: 訂單項目 id
	public String editPurchaseItem(@PathVariable("pid") Long pid, @PathVariable("iid") Long iid, Model model) {
		PurchaseDto purchaseDto = purchaseService.getPurchaseDtoById(pid);
		PurchaseItemDto purchaseItemDto = purchaseService.getPurchaseItemDtoById(iid);
		List<ProductDto> productDtos = productService.findAll();
		model.addAttribute("purchaseDto", purchaseDto);
		model.addAttribute("purchaseItemDto", purchaseItemDto);
		model.addAttribute("productDtos", productDtos);
		return "purchase-item";
	}
	
	// 採購單明細-修改
	@PutMapping("/{pid}/item/{iid}")
	public String updatePurchaseItem(PurchaseItemDto purchaseItemDto, 
		@PathVariable("pid") Long pid, 
		@PathVariable("iid") Long iid) {
		purchaseService.updatePurchaseItem(purchaseItemDto, iid);
		return "redirect:/purchase/" + pid + "/item";
	}
	
	// 採購單明細-刪除
	@GetMapping("/delete/{pid}/item/{iid}")
	public String deletePurchaseItem(@PathVariable("pid") Long pid, @PathVariable("iid") Long iid) {
		purchaseService.deletePurchaseItem(iid);
		return "redirect:/purchase/" + pid + "/item";
	} 
	
}

