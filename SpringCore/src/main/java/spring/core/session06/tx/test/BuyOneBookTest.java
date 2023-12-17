package spring.core.session06.tx.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session06.tx.controller.BookController;

public class BuyOneBookTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config-tx.xml");
		BookController bookController = ctx.getBean(BookController.class);
		//bookController.buyOneBook("John", 1);
		//bookController.buyOneBook("John", 2);
		bookController.buyOneBook("John", 1);
	}

}
