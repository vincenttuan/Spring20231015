package com.psi.testing.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.psi.model.dto.SupplierDto;
import com.psi.service.SupplierService;

@SpringBootTest
public class CreateSupplier {
	
	@Autowired
	private SupplierService supplierService;
	
	@Test
	public void addTest() {
		SupplierDto s1 = new SupplierDto();
		s1.setName("富貴芬芳");
		
		SupplierDto s2 = new SupplierDto();
		s2.setName("瑞士花園");
		
		SupplierDto s3 = new SupplierDto();
		s3.setName("伊莉莎白");
		
		// 儲存
		supplierService.add(s1);
		supplierService.add(s2);
		supplierService.add(s3);
		
		System.out.println("Save OK");
		
	}
}
