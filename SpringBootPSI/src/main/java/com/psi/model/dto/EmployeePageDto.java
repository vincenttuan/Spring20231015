package com.psi.model.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;

/**
 * EmployeePageDto 是用於封裝與員工分頁相關信息的數據傳輸對象（DTO）。
 * 這個類提供了必要的信息來支持在前端進行分頁顯示。
 */
@Getter
@Setter
public class EmployeePageDto {
	private List<EmployeeDto> employeeDtos; // 保存分頁查詢結果的列表
	private int curentPage; // 保存當前頁碼
	private int totalPage; // 保存總頁數
	
	/**
     * 通過從 Page 物件轉換來建構 EmployeePageDto 物件。
     * @param deptPage Page<EmployeeDto> 物件，包含從數據庫查詢到的分頁結果。
     */
	public EmployeePageDto(Page<EmployeeDto> empPageDto) {
		this.employeeDtos = empPageDto.getContent(); // 獲取當前頁的數據列表
		this.curentPage = empPageDto.getNumber(); // 獲取當前頁碼
		this.totalPage = empPageDto.getTotalPages(); // 獲取總頁數
	}
}
