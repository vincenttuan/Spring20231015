package com.psi.model.po;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders") // 因為 MySQL 不可以建立 Order 資料表 (因 Order 在 MySQL 中是保留字)
@Getter
@Setter
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 訂單序號
	
	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date; // 訂單日期
	
	@JoinColumn(name = "customer_id")
	@ManyToOne
	private Customer customer;
	
	@OneToMany(mappedBy = "order")
	@OrderBy("id ASC")
	private Set<OrderItem> orderItems = new LinkedHashSet<>();
	
	@JoinColumn(name = "employee_id")
	@ManyToOne
	private Employee employee;

	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", customer=" + customer + ", employee=" + employee + "]";
	} 
	
	
}	

