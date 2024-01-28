package com.psi.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psi.model.dto.EmployeeDto;
import com.psi.model.dto.ProductDto;
import com.psi.model.dto.PurchaseDto;
import com.psi.model.dto.PurchaseItemDto;
import com.psi.model.dto.SupplierDto;
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
	
	// 新增
	@Transactional
	public Long add(PurchaseDto purchaseDto) {
	    Purchase purchase = modelMapper.map(purchaseDto, Purchase.class);
	    Purchase savedPurchase = purchaseRepository.saveAndFlush(purchase);
	    return savedPurchase.getId(); // 返回保存後的 ID
	}

	
	// 修改
	public void update(PurchaseDto purchaseDto, Long id) {
		Optional<Purchase> purchaseOpt = purchaseRepository.findById(id);
		if(purchaseOpt.isPresent()) {
			Purchase purchase = purchaseOpt.get();
			purchase.setDate(purchaseDto.getDate());
			
			EmployeeDto employeeDto = purchaseDto.getEmployee();
			Employee employee = modelMapper.map(employeeDto, Employee.class);
			purchase.setEmployee(employee);
			
			SupplierDto supplierDto = purchaseDto.getSupplier();
			Supplier supplier = modelMapper.map(supplierDto, Supplier.class);
			purchase.setSupplier(supplier);
			
			purchaseRepository.save(purchase);
		} 
	}
	
	// 刪除
	public void delete(Long id) {
		Optional<Purchase> purchaseOpt = purchaseRepository.findById(id);
		if(purchaseOpt.isPresent()) {
			purchaseRepository.delete(purchaseOpt.get());
		} 
	}
		
	// 查詢單筆
	public PurchaseDto getPurchaseDtoById(Long id) {
		Optional<Purchase> purchaseOpt = purchaseRepository.findById(id);
		if(purchaseOpt.isPresent()) {
			Purchase purchase = purchaseOpt.get();
			//PurchaseDto purchaseDto = modelMapper.map(purchase, PurchaseDto.class);
			PurchaseDto purchaseDto = new PurchaseDto();
			purchaseDto.setId(purchase.getId());
			purchaseDto.setDate(purchase.getDate());
			purchaseDto.setEmployee(modelMapper.map(purchase.getEmployee(), EmployeeDto.class));
			purchaseDto.setSupplier(modelMapper.map(purchase.getSupplier(), SupplierDto.class));
			Set<PurchaseItemDto> purchaseItems = new LinkedHashSet<>();
			
			for(PurchaseItem item : purchase.getPurchaseItems()) {
				PurchaseItemDto purchaseItemDto = new PurchaseItemDto();
				purchaseItemDto.setId(item.getId());
				purchaseItemDto.setAmount(item.getAmount());
				purchaseItemDto.setProduct(modelMapper.map(item.getProduct(), ProductDto.class));
				purchaseItems.add(purchaseItemDto);
			}
			
			purchaseDto.setPurchaseItems(purchaseItems);
			
			return purchaseDto;
		}
		return null;
	}
	
	// 全部查詢
	public List<PurchaseDto> findAll() {
		List<Purchase> purchases = purchaseRepository.findAll();
		return purchases.stream()
						  .map(purchase -> getPurchaseDtoById(purchase.getId()))
						  .toList();
	}
	
	//-------------------------------------------------------------------------------------
	
	// 新增訂單項目
	public void addItem(PurchaseItemDto purchaseItemDto, Long pid) {
		// 訂單明細
		PurchaseItem purchaseItem = modelMapper.map(purchaseItemDto, PurchaseItem.class);
		// 訂單檔(主檔)
		Purchase purchase = purchaseRepository.findById(pid).get();
		// 訂單項目與訂單檔(主檔)建立關聯 (ps:由多的一方建立關聯)
		purchaseItem.setPurchase(purchase);
		purchaseItemRepository.save(purchaseItem);
	}
	
	// 查詢訂單項目單筆
	public PurchaseItemDto getPurchaseItemDtoById(Long id) {
		Optional<PurchaseItem> purchaseItemOpt = purchaseItemRepository.findById(id);
		if(purchaseItemOpt.isPresent()) {
			PurchaseItem purchaseItem = purchaseItemOpt.get();
			//PurchaseItemDto purchaseItemDto = modelMapper.map(purchaseItem, PurchaseItemDto.class);
			PurchaseItemDto purchaseItemDto = new PurchaseItemDto();
			purchaseItemDto.setId(purchaseItem.getId());
			purchaseItemDto.setAmount(purchaseItem.getAmount());
			purchaseItemDto.setProduct(modelMapper.map(purchaseItem.getProduct(), ProductDto.class));
			return purchaseItemDto;
		}
		return null;
	}
	
	// 修改訂單項目
	public void updatePurchaseItem(PurchaseItemDto purchaseItemDto, Long pid) {
		// 訂單明細
		PurchaseItem purchaseItem = modelMapper.map(purchaseItemDto, PurchaseItem.class);
		// 訂單檔(主檔)
		Purchase purchase = purchaseRepository.findById(pid).get();
		// 訂單項目與訂單檔(主檔)建立關聯 (ps:由多的一方建立關聯)
		purchaseItem.setPurchase(purchase);
		purchaseItemRepository.save(purchaseItem);		
	}
	
	// 刪除訂單項目
	public void deletePurchaseItem(Long iid) {
		purchaseItemRepository.deleteById(iid);
	}
	
	
	
}
