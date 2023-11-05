package spring.core.session03.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
		user.setNickname(new String[] {"Foo", "Bar"});
		user.setSubjects(new HashSet<>(List.of("Programming", "Math")));
		user.setScores(List.of(100, 90));
		//Map<String, String> hobbies = new HashMap<>();
		//hobbies.put("1", "Game");
		//hobbies.put("2", "RC model");
		//user.setHobbies(hobbies);
		user.setHobbies(Map.of("1", "Game", "2", "RC Model"));
		
		System.out.println(user);
		
		User user2 = ctx.getBean("user", User.class);
		System.out.println(user2);
		
		UserController userController = ctx.getBean("userController", UserController.class);
		userController.appendUser(user); // 加入 user
		userController.appendUser(user2); // 加入 user
		
		List<User> users = userController.findUsers();
		System.out.println("資料筆數: " + users.size());
		System.out.println("所有資料: " + users);

	}

}
