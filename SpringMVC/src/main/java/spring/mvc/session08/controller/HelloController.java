package spring.mvc.session08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	/*
	 * 2. ? 帶參數
	 * 網址: http://localhost:8080/SpringMVC/mvc/hello/sayhi?name=John&age=20
	 * 網址: http://localhost:8080/SpringMVC/mvc/hello/sayhi?name=Mary
	 * 限制: name 參數是一定要有的(預設), 
	 *      age 參數是不一定要有的, 初始值 = 0
	 * */
	@GetMapping(value = "/sayhi")
	@ResponseBody
	public String sayhi(@RequestParam(value = "name", required = true) String name,
						@RequestParam(value = "age", required = false, defaultValue = "0") Integer age) {
		
		String message = String.format("Hi %s, %d", name, age);
		return message;
	}
	
}
