package spring.core.session03.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session03.mvc.model.bean.User;

public class TestUser {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config3-mvc.xml");
		User user = ctx.getBean("user", User.class);
		System.out.println(user);
	}

}
