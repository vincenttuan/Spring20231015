package com.psi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * URL 路徑設計
 * GET  /employee/?page0&size=5 分頁查詢
 * GET  /employee/edit/{id} 單筆查詢(修改用)
 * POST /employee/ 新增
 * PUT  /employee/{id} 修改
 * GET  /employee/delete/{id} 刪除
 * */

@Controller
@RequestMapping("/employee")
public class EmployeeController {

}
