package com.psi.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.psi.model.dto.OrderItemDto;
import com.psi.model.vo.Inventory;
import com.psi.service.ProductService;

@Component
public class InventoryValidator implements Validator {
	
	@Autowired
	private ProductService productService;

	@Override
	public boolean supports(Class<?> clazz) {
		return OrderItemDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OrderItemDto orderItemDto = (OrderItemDto)target;
		
		if(orderItemDto.getAmount() == null || orderItemDto.getAmount() == 0) {
			errors.rejectValue("amount", "order_itemDto.amount.required", "請輸入數量");
		} else {
			// 此商品的庫存數量是否足夠下單 ?
			Long id = orderItemDto.getProduct().getId();
			Inventory inventory = productService.findInventoryById(id);
			// amount1 : 進貨數量
			// amount2 : 銷售數量
			// remaining : 庫存剩餘數量 (進貨數量 - 銷售數量)
			int amount1 = inventory.getAmount1() == null ? 0 : inventory.getAmount1();
			int amount2 = inventory.getAmount2() == null ? 0 : inventory.getAmount2();
			int remaining = amount1 - amount2;
			// 購買數量是否大於庫存剩餘數量
			if(orderItemDto.getAmount() > remaining) {
				errors.rejectValue("amount", "order_itemDto.amount.insufficient", "目前庫存數量不足 (庫存: " + remaining + ")");
			}
			
		}
		
		
	}
	
	
}
