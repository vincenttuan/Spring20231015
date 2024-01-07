package com.psi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.psi.dto.DepartmentDTO;
import com.psi.dto.DepartmentPageDTO;
import com.psi.entity.Department;
import com.psi.repository.DepartmentRepository;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;
	
	/**
	 * 它用於處理與部門相關的業務邏輯。主要功能是從 DepartmentRepository 獲取分頁的部門數據，然後將這些數據轉換為 DTO（數據傳輸對象）。
	 * 這種轉換過程有助於將數據層和表示層分離，提高了代碼的可維護性和靈活性。轉換後的 DTO 數據被包裝在 DepartmentPageDTO 中，
	 * 這個對象既包含了分頁資訊，也包含了部門數據的列表，方便前端展示和處理。
	 * */
	public DepartmentPageDTO findAllDepartments(Pageable pageable) {
		Page<Department> deptPage = departmentRepository.findAll(pageable); // 從資料庫獲取分頁的部門數據
		Page<DepartmentDTO> dtoPage = deptPage.map(this::convertToDTO); // 將部門實體數據轉換為 DTO
		return new DepartmentPageDTO(dtoPage); // 返回包含 DTO 的分頁對象
	}
	
	/**
	 * 將部門實體數據轉換為 DTO
	 * */
	private DepartmentDTO convertToDTO(Department department) {
		DepartmentDTO dto = new DepartmentDTO(); // 創建一個新的 DepartmentDTO 物件
		dto.setId(department.getId()); // 實體數據 department 的 id 配置給 dto 的 id
		dto.setName(department.getName()); // 實體數據 department 的 name 配置給 dto 的 name
		return dto;
	}
}
