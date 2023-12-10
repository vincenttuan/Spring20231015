package spring.mvc.session09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session09.entity.User;

@Controller
@RequestMapping("/session09/user")
public class UserController {
	
	// 首頁
	@GetMapping("/")
	public String index(@ModelAttribute User user, Model model) {
		
		return "session09/user";
	}
	
	
}
