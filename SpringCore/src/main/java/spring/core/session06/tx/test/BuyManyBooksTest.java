package spring.core.session06.tx.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session06.tx.controller.BookController;

public class BuyManyBooksTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config-tx.xml");
		BookController bookController = ctx.getBean(BookController.class);
		bookController.buyManyBooks("John", 1, 2, 1);
		
	}

}
