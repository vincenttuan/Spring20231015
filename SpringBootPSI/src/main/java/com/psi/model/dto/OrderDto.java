package com.psi.model.dto;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
private Long id; // 訂單序號
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date; // 訂單日期
	private CustomerDto customer;
	private Set<OrderItemDto> orderItems = new LinkedHashSet<>();
	private EmployeeDto employee;
	
	// 計算訂單總價
	public Integer getTotal() {
		if(orderItems.size() == 0) {
			return 0;
		}
		return orderItems.stream()
				.mapToInt(item -> item.getAmount() * item.getProduct().getPrice())
				.sum();
	}
}

