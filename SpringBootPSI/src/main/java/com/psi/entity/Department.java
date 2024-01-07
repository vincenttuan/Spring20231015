package com.psi.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "department") // 預設(可以不寫)
@Getter
@Setter
public class Department {
	
	// @Id: 主鍵
	// @GeneratedValue: 自動產生值
	// strategy = GenerationType.IDENTITY : 自動增長, 適用於 MySQL
	// strategy = GenerationType.SEQUENCE : 自動增長, 適用於 Oracle
	// strategy = GenerationType.TABLE : 自動增長, 適用於所有資料庫
	// GenerationType.TABLE 會建立一個資料表, 用來紀錄目前的 id 值, 並且鎖定該筆資料, 避免同時新增時發生重複
	// strategy = GenerationType.AUTO : 自動增長, 由系統自動選擇適合的策略
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 部門序號
	
	//@Column(name = "name", unique = false, nullable = true, length = 255) // 預設
	@Column(name = "name", unique = true, nullable = false, length = 50) // 自訂
	private String name; // 部門名稱
	
	// mappedBy = "department" 表示此集合的內容是由 Employee 的 department 來維護
	// fetch = FetchType.LAZY (預設)表示預設不會主動查詢該部門的員工(有用到時才會去查詢)
	// fetch = FetchType.EAGER 表示預設會主動查詢該部門的員工(不論是否會用都會先查好)
	// orphanRemoval = true 表示如果該部門下所有 Employee 被刪除, 則此部門也會被刪除(在此不適用)
	// orphanRemoval = false (預設)表示如果該部門下所有 Employee 被刪除, 則此部門並不會被刪除
	@OneToMany(
			mappedBy = "department", 
			fetch = FetchType.LAZY,
			orphanRemoval = false)
	private Set<Employee> employees = new LinkedHashSet<>(); 
	
}
