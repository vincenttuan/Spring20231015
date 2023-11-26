package spring.core.group_buy.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.group_buy.conf.GroupBuyConfig;
import spring.core.group_buy.dao.GroupBuyDao;
import spring.core.group_buy.dao.GroupBuyDaoMySQL;
import spring.core.group_buy.entity.User;

public class SimpleSelectTest {

	public static void main(String[] args) {
		/*
		GroupBuyDao dao = new GroupBuyDaoMySQL();
		List<User> users = dao.findAllUsers();
		System.out.println(users.size());
		System.out.println(users);
		*/
		
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config-group-buy.xml");
		ApplicationContext ctx = new AnnotationConfigApplicationContext(GroupBuyConfig.class);
		GroupBuyDao dao = ctx.getBean("groupBuyDaoMySQL", GroupBuyDaoMySQL.class);
		
		// 測試新增
		User user = new User();
		user.setUsername("John");
		user.setPassword("1234");
		user.setLevel(2);
		dao.addUser(user);
		
		// 查詢使用者
		List<User> users = dao.findAllUsers();
		System.out.println(users.size());
		System.out.println(users);
	}

}
