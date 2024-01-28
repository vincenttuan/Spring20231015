package com.psi.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psi.model.dto.PurchaseDto;
import com.psi.model.dto.PurchaseItemDto;
import com.psi.model.po.Employee;
import com.psi.model.po.Purchase;
import com.psi.model.po.PurchaseItem;
import com.psi.model.po.Supplier;
import com.psi.repository.PurchaseItemRepository;
import com.psi.repository.PurchaseRepository;

import jakarta.transaction.Transactional;

@Service
public class PurchaseService {
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private PurchaseItemRepository purchaseItemRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// 採購單 -------------------------------------------------
	// 新增
	@Transactional
	public Long add(PurchaseDto purchaseDto) {
		Purchase purchase = modelMapper.map(purchaseDto, Purchase.class);
		Purchase savePurchase = purchaseRepository.saveAndFlush(purchase);
		return savePurchase.getId(); // 得到最新 ID
	} 
	
	// 修改
	@Transactional
	public void update(PurchaseDto purchaseDto, Long id) {
		Optional<Purchase> purchaseOpt = purchaseRepository.findById(id);
		if(purchaseOpt.isPresent()) {
			Purchase purchase = purchaseOpt.get();
			// 修改日期
			purchase.setDate(purchaseDto.getDate()); 
			
			// 修改員工
			Employee employee = modelMapper.map(purchaseDto.getEmployee(), Employee.class);
			purchase.setEmployee(employee);
			
			// 修改供應商
			Supplier supplier = modelMapper.map(purchaseDto.getSupplier(), Supplier.class);
			purchase.setSupplier(supplier);
			
			purchaseRepository.save(purchase);
		}
	}
	
	// 刪除
	@Transactional
	public void delete(Long id) {
		Optional<Purchase> purchaseOpt = purchaseRepository.findById(id);
		if(purchaseOpt.isPresent()) {
			//purchaseRepository.delete(purchaseOpt.get());
			purchaseRepository.deleteById(id);
		}
	}
	
	// 查詢-單筆
	public PurchaseDto getPurchaseDtoById(Long id) {
		Optional<Purchase> purchaseOpt = purchaseRepository.findById(id);
		if(purchaseOpt.isPresent()) {
			return modelMapper.map(purchaseOpt.get(), PurchaseDto.class);
		}
		return null;
	}
	
	// 查詢-多筆(全部)
	public List<PurchaseDto> findAll() {
		List<Purchase> purchases = purchaseRepository.findAll();
		List<PurchaseDto> purchaseDtos = purchases.stream()
				.map(purchase -> modelMapper.map(purchase, PurchaseDto.class)).toList();
		return purchaseDtos;
	}
	
	// 採購單明細 -----------------------------------------------
	// 新增
	@Transactional
	// pid: 採購單主檔的序號
	public void addPurchaseItem(PurchaseItemDto purchaseItemDto, Long pid) {
		// 採購單明細 po
		PurchaseItem purchaseItem = modelMapper.map(purchaseItemDto, PurchaseItem.class);
		// 採購單(主檔) po
		Purchase purchase = purchaseRepository.findById(pid).get();
		// 採購單明細 po 與 採購單(主檔) po 建立關聯 (ps:由多的一方建立關聯)
		purchaseItem.setPurchase(purchase);
		// 儲存
		purchaseItemRepository.save(purchaseItem);
	}
	
	// 修改
	
	
	// 刪除
	
	
	// 查詢-單筆
	
}
