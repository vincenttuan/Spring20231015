package spring.core.session03.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session03.mvc.controller.UserController;
import spring.core.session03.mvc.model.bean.User;

public class TestMVC {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config3-mvc.xml");
		
		User user = ctx.getBean("user", User.class);
		user.setUsername("John");
		user.setAge(18);
		
		UserController userController = ctx.getBean("userController", UserController.class);
		userController.appendUser(user); // 加入 user
		
		List<User> users = userController.findUsers();
		System.out.println("資料筆數: " + users.size());
		System.out.println("所有資料: " + users);

	}

}
