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
import org.springframework.web.bind.annotation.ResponseBody;

import com.psi.model.dto.EmployeeDto;
import com.psi.model.dto.ProductDto;
import com.psi.model.dto.PurchaseDto;
import com.psi.model.dto.PurchaseItemDto;
import com.psi.model.dto.SupplierDto;
import com.psi.service.EmployeeService;
import com.psi.service.ProductService;
import com.psi.service.PurchaseService;
import com.psi.service.SupplierService;

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
	
	@PostMapping("/")
	public String create(PurchaseDto purchaseDao) {
		purchaseService.add(purchaseDao);
		return "redirect:/purchase/";
	}
	
	@GetMapping("/edit/{id}") // 修改頁面的呈現
	public String edit(@PathVariable("id") Long id, Model model) {
		PurchaseDto purchaseDto = purchaseService.getPurchaseDtoById(id);
		
		List<SupplierDto> supplierDtos = supplierService.findAll();
		List<EmployeeDto> employeeDtos = employeeService.findAll();
		
		model.addAttribute("purchaseDto", purchaseDto);
		model.addAttribute("supplierDtos", supplierDtos);
		model.addAttribute("employeeDtos", employeeDtos);
		return "purchase-edit";
	}
	
	@PutMapping("/{id}") // 對資料庫進行修改
	public String update(@PathVariable("id") Long id, PurchaseDto purchaseDto) {
		purchaseService.update(purchaseDto, id);
		return "redirect:/purchase/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		purchaseService.delete(id);
		return "redirect:/purchase/";
	}
	
	
	// 檢視採購單明細
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
	
	@PostMapping("/{pid}/item")
	// 新增訂單項目, pid: 訂單主檔 id
	public String addPurchaseItem(PurchaseItemDto purchaseItemDto, @PathVariable("pid") Long pid) {
		// 將訂單項目加入到指定 pid 的訂單主檔
		purchaseService.addItem(purchaseItemDto, pid);
		return "redirect:/purchase/" + pid + "/item";
	}
	
	@GetMapping("/edit/{pid}/item/{iid}") // 編輯修改頁面的呈現
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
	
	@PutMapping("/{pid}/item")
	// 修改訂單項目
	public String updatePurchaseItem(PurchaseItemDto purchaseItemDto, @PathVariable("pid") Long pid) {
		purchaseService.updatePurchaseItem(purchaseItemDto, pid);
		return "redirect:/purchase/" + pid + "/item";
	}
	
	@GetMapping("/delete/{pid}/item/{iid}")
	// 刪除採購明細檔
	public String deletePurchaseItem(@PathVariable("pid") Long pid, @PathVariable("iid") Long iid) {
		purchaseService.deletePurchaseItem(iid);
		return "redirect:/purchase/" + pid + "/item";
	} 
	
}


