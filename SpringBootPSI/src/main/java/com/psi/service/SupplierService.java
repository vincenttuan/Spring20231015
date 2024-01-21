package com.psi.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psi.model.dto.SupplierDto;
import com.psi.model.po.Supplier;
import com.psi.repository.SupplierRepository;

@Service
public class SupplierService {
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private ModelMapper modelMapper; // Dto 與 Po 之間互轉的工具
	
	// 新增
	// 1.得到從 SupplierController 來的 Dto
	// 2.將 Dto 轉 Po
	// 3.將 Po 透過 supplierRepository 存入到資料表
	public void add(SupplierDto supplierDto) {
		// Dto 轉 Po
		Supplier supplier = modelMapper.map(supplierDto, Supplier.class);
		supplierRepository.save(supplier);
	}
	
	// 修改
	// 1.得到從 SupplierController 來的 Dto(要修改的內容) 與 id(要修改紀錄的 id)
	// 2.透過 id 得到該筆紀錄的 po
	// 3.將 dto 的資料設定給 po
	// 4.儲存/修改
	public void update(SupplierDto supplierDto, Long id) {
		Optional<Supplier> supplierOpt = supplierRepository.findById(id);
		if(supplierOpt.isPresent()) { // 是否有該筆紀錄
			Supplier supplier = supplierOpt.get();
			supplier.setName(supplierDto.getName());
			supplierRepository.save(supplier); // 因為 supplier 內含有 id, 所以會進行修改而非新增
		}
	}
	
	// 刪除
	// 根據 id 找到該筆紀錄
	// 若有該筆紀錄就進行刪除
	public void delete(Long id) {
		Optional<Supplier> supplierOpt = supplierRepository.findById(id);
		if(supplierOpt.isPresent()) {
			supplierRepository.deleteById(id); // 根據 id 來刪除紀錄
			//supplierRepository.delete(supplierOpt.get()); // 直接刪除紀錄
		}
	}
	
	// 查詢單筆
	public SupplierDto getSupplierById(Long id) {
		Optional<Supplier> supplierOpt = supplierRepository.findById(id);
		if(supplierOpt.isPresent()) {
			Supplier supplier = supplierOpt.get(); // po
			SupplierDto supplierDto = modelMapper.map(supplier, SupplierDto.class); // po 轉 Dto
			return supplierDto;
		}
		return null;
	}
	
	// 全部查詢
}
