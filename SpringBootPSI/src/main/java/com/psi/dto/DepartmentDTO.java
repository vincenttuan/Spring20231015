package com.psi.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DepartmentDTO（Data Transfer Object，數據傳輸對象）是一個用於封裝數據並在應用程序的不同層之間傳輸數據的模式。這個類別的用途主要包括：
 * 數據封裝：它封裝了與部門相關的數據，如 id 和 name。這可以包括從數據庫獲取的數據，或者是需要在客戶端展示或處理的數據。
 * 分層溝通：DTO 在不同的應用層之間傳輸數據，如從持久層（比如 JPA 存儲庫）到業務邏輯層（Service），再到表示層（比如 Spring MVC 控制器）。
 * 隔離層次之間的依賴：使用 DTO 可以讓各層不直接依賴於數據實體（Entity），這有助於降低模塊間的耦合度。例如，您可以在 DTO 中只包含客戶端所需的數據，而不是整個數據實體。
 * 安全性：它可以避免直接將實體對象暴露給客戶端，從而保護了數據模型的完整性。這也有助於防止意外暴露敏感信息。
 * 靈活性：DTO 允許您根據前端的需要定制所需的數據結構，這比直接使用數據實體更為靈活。
*/
@Getter
@Setter
public class DepartmentDTO {
	private Long id;
	private String name;
	// 可以加其他業務資料
}
