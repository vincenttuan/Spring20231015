package com.psi.model.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
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
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "purchase")
@Getter
@Setter
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 採購單序號
	
	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date; // 採購日期
	
	@JoinColumn(name = "supplier_id") // 供應商序號(外鍵)
	@ManyToOne
	private Supplier supplier;
	
	@JoinColumn(name = "employee_id") // 員工序號(外鍵)
	@ManyToOne
	private Employee employee;
	
	@OneToMany(mappedBy = "purchase", fetch = FetchType.EAGER)
	@OrderBy("id ASC")
	private Set<PurchaseItem> purchaseItems = new LinkedHashSet<>();
	//private List<PurchaseItem> purchaseItems = new ArrayList<>();

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", date=" + date + ", supplier=" + supplier + ", employee=" + employee + "]";
	}
	
	
}
