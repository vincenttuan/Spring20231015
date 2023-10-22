package spring.core.session02.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session02.bean.Author;

public class TestAuthor {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config2.xml");
		Author author1 = ctx.getBean("author1", Author.class);
		System.out.println(author1);
		// 手動注入
		author1.setName("Bob");
		author1.setSex('男');
		author1.setAge(25);
		System.out.println(author1);
	}

}
