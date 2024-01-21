package com.psi.testing.read;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.psi.model.dto.SupplierDto;
import com.psi.service.SupplierService;

@SpringBootTest
public class ReadSupplier {
	
	@Autowired
	private SupplierService supplierService;
	
	@Test
	public void findOne() {
		long id = 1; // 1L
		SupplierDto supplierDto = supplierService.getSupplierById(id);
		System.out.println(supplierDto);
	}
	
	@Test
	public void findAll() {
		List<SupplierDto> supplierDtos = supplierService.findAll();
		System.out.println(supplierDtos);
	}
}
