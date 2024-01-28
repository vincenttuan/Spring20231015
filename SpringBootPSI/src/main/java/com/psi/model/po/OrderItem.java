package com.psi.model.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 訂單細目序號
	
	@Column
	private Integer amount; // 訂購數量
	
	@Column
	private Integer price; // 商品售價
	
	@JoinColumn(name = "order_id")
	@ManyToOne
	private Order order;
	
	@JoinColumn(name = "product_id")
	@ManyToOne
	private Product product;

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", amount=" + amount + ", price=" + price + ", order=" + order + ", product="
				+ product + "]";
	}
	
}

