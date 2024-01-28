package com.psi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
	private Long id; // 訂單細目序號
	private Integer amount; // 訂購數量
	private Integer price; // 商品售價
	private OrderDto order;
	private ProductDto product;
}
