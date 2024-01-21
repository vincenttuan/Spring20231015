package com.psi.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psi.model.dto.CustomerDto;
import com.psi.model.po.Customer;
import com.psi.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ModelMapper modelMapper; // Dto 與 Po 之間互轉的工具
	
	// 新增
	public void add(CustomerDto customerDto) {
		// Dto 轉 Po
		Customer customer = modelMapper.map(customerDto, Customer.class);
		customerRepository.save(customer);
	}
	
	// 修改
	public void update(CustomerDto customerDto, Long id) {
		Optional<Customer> customerOpt = customerRepository.findById(id);
		if(customerOpt.isPresent()) { // 是否有該筆紀錄
			Customer customer = customerOpt.get();
			customer.setName(customerDto.getName());
			customerRepository.save(customer);
		}
	}
	
	// 刪除
	// 根據 id 找到該筆紀錄
	// 若有該筆紀錄就進行刪除
	public void delete(Long id) {
		Optional<Customer> customerOpt = customerRepository.findById(id);
		if(customerOpt.isPresent()) {
			customerRepository.deleteById(id); // 根據 id 來刪除紀錄
		}
	}
	
	// 查詢單筆
	public CustomerDto getCustomerDtoById(Long id) {
		Optional<Customer> customerOpt = customerRepository.findById(id);
		if(customerOpt.isPresent()) {
			Customer customer = customerOpt.get(); // po
			CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class); // po 轉 Dto
			return customerDto;
		}
		return null;
	}
	
	// 全部查詢
	public List<CustomerDto> findAll() {
		List<Customer> customers = customerRepository.findAll();
		List<CustomerDto> customerDtos = customers.stream()
				.map(customer -> modelMapper.map(customer, CustomerDto.class))
				.toList();
		return customerDtos;
	}
}



