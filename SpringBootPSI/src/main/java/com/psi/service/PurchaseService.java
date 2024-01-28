package com.psi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psi.repository.PurchaseItemRepository;
import com.psi.repository.PurchaseRepository;

@Service
public class PurchaseService {
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private PurchaseItemRepository purchaseItemRepository;
	
	// 採購單 -------------------------------------------------
	// 新增
	// 修改
	// 刪除
	// 查詢-單筆
	// 查詢-多筆(全部)
	
	// 採購單明細 -----------------------------------------------
	// 新增
	// 修改
	// 刪除
	// 查詢-單筆
	
}
