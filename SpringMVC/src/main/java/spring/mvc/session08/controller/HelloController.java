package spring.mvc.session08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// Base url      : http://localhost:8080/SpringMVC/mvc
// Controller url: http://localhost:8080/SpringMVC/mvc/hello
@Controller
@RequestMapping(value = "/hello")
public class HelloController {
	
	/*
	 * 1. 取得 Welcome 字串服務
	 * 網址: http://localhost:8080/SpringMVC/mvc/hello/welcome
	 * */
	@GetMapping(value = "/welcome")
	@ResponseBody
	public String welcome() {
		return "Welcome Spring MVC!";
	}
	
}
