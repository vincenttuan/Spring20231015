package com.psi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.psi.model.dto.CustomerDto;
import com.psi.model.dto.EmployeeDto;
import com.psi.model.dto.OrderDto;
import com.psi.model.dto.OrderItemDto;
import com.psi.model.dto.ProductDto;
import com.psi.service.CustomerService;
import com.psi.service.EmployeeService;
import com.psi.service.OrderService;
import com.psi.service.ProductService;
import com.psi.validator.InventoryValidator;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService ;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private InventoryValidator inventoryValidator;
	
	@GetMapping("/")
	public String index(Model model) {
		OrderDto orderDto = new OrderDto();
		List<OrderDto> orderDtos = orderService.findAll();
		List<CustomerDto> customerDtos = customerService.findAll();
		List<EmployeeDto> employeeDtos = employeeService.findAll();
		model.addAttribute("orderDto", orderDto);
		model.addAttribute("orderDtos", orderDtos);
		model.addAttribute("customerDtos", customerDtos);
		model.addAttribute("employeeDtos", employeeDtos);
		return "order";
	}
	
	@PostMapping("/")
	public String create(OrderDto orderDto) {
		orderService.add(orderDto);
		return "redirect:/order/";
	}
	
	@GetMapping("/edit/{id}") // 修改頁面的呈現
	public String edit(@PathVariable("id") Long id, Model model) {
		OrderDto orderDto = orderService.getOrderDtoById(id);
		List<CustomerDto> customerDtos = customerService.findAll();
		List<EmployeeDto> employeeDtos = employeeService.findAll();
		model.addAttribute("orderDto", orderDto);
		model.addAttribute("customerDtos", customerDtos);
		model.addAttribute("employeeDtos", employeeDtos);
		return "order-edit";
	}
	
	@PutMapping("/{id}") // 對資料庫進行修改
	public String update(@PathVariable("id") Long id, OrderDto orderDto) {
		orderDto.setId(id);
		orderService.update(orderDto, id);
		return "redirect:/order/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		orderService.delete(id);
		return "redirect:/order/";
	}
	
	// 檢視訂單明細
	// oid -> 訂單主檔 id
	@GetMapping("/{oid}/item")
	public String indexItem(Model model, @PathVariable("oid") Long oid) {
		OrderDto orderDto = orderService.getOrderDtoById(oid);
		OrderItemDto orderItemDto = new OrderItemDto();
		List<ProductDto> productDtos = productService.findAll();
		model.addAttribute("orderDto", orderDto);
		model.addAttribute("orderItemDto", orderItemDto);
		model.addAttribute("productDtos", productDtos);
		return "order-item";
	}
	
	@PostMapping("/{oid}/item")
	// 新增訂單項目
	public String createItem(OrderItemDto orderItemDto, BindingResult result, Model model, @PathVariable("oid") Long oid) {
		// 驗證資料
		inventoryValidator.validate(orderItemDto, result);
		if(result.hasErrors()) {
			OrderDto orderDto = orderService.getOrderDtoById(oid);
			List<ProductDto> productDtos = productService.findAll();
			model.addAttribute("orderDto", orderDto);
			model.addAttribute("orderItemDto", orderItemDto);
			model.addAttribute("productDtos", productDtos);
			return "order-item";
		}
		orderService.addItem(orderItemDto, oid);
		return "redirect:/order/" + oid + "/item";
	}
	
	@GetMapping("/edit/{oid}/item/{iid}") // 項目修改頁面的呈現
	public String editItem(@PathVariable("oid") Long oid, @PathVariable("iid") Long iid, Model model) {
		OrderDto orderDto = orderService.getOrderDtoById(oid);
		OrderItemDto orderItemDto = orderService.getOrderItemDtoById(iid);
		List<ProductDto> productDtos = productService.findAll();
		model.addAttribute("orderDto", orderDto);
		model.addAttribute("orderItemDto", orderItemDto);
		model.addAttribute("productDtos", productDtos);
		return "order-item";
	}
	
	@PutMapping("/{oid}/item")
	// 修改訂單項目
	public String updateItem(OrderItemDto orderItemDto, @PathVariable("oid") Long oid) {
		orderService.updateOrderItem(orderItemDto, oid);
		return "redirect:/order/" + oid + "/item";
	}
	
	@GetMapping("/delete/{oid}/item/{iid}")
	// 刪除採購明細檔
	public String deleteItem(@PathVariable("oid") Long oid, @PathVariable("iid") Long iid) {
		orderService.deleteOrderItem(iid);
		return "redirect:/order/" + oid + "/item";
	} 
	
}
