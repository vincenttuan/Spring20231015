package spring.core.session03.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import spring.core.session03.mvc.model.bean.User;
import spring.core.session03.mvc.model.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	public void appendUser(User user) {
		System.out.println("呼叫 UserController appendUser()");
		userService.addUser(user);
	}
	
	public List<User> findUsers() {
		return userService.queryUsers();
	}
	
}
