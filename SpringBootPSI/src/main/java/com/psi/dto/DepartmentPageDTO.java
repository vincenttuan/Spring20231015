package com.psi.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import com.psi.entity.Department;

import lombok.Getter;
import lombok.Setter;

/**
 * DepartmentPageDTO 是用於封裝與部門分頁相關信息的數據傳輸對象（DTO）。
 * 這個類提供了必要的信息來支持在前端進行分頁顯示。
 */
@Getter
@Setter
public class DepartmentPageDTO {
	private List<DepartmentDTO> departments; // 存儲分頁查詢結果的列表
	private int currentPage; // 當前頁碼
	private int totalPage; // 總頁數
	
    /**
     * 通過從 Page 物件轉換來構造 DepartmentPageDTO。
     * @param deptPage Page<DepartmentDTO> 物件，包含從數據庫查詢到的分頁結果。
     */
	public DepartmentPageDTO(Page<DepartmentDTO> deptPage) {
		this.departments = deptPage.getContent(); // 獲取當前頁的數據列表
		this.currentPage = deptPage.getNumber(); // 獲取當前頁碼
		this.totalPage = deptPage.getTotalPages(); // 獲取總頁數
	}
}
