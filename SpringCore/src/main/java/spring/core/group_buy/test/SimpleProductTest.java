package spring.core.group_buy.test;

import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.group_buy.conf.GroupBuyConfig;
import spring.core.group_buy.dao.GroupBuyDao;
import spring.core.group_buy.dao.GroupBuyDaoMySQL;
import spring.core.group_buy.entity.Product;
import spring.core.group_buy.entity.User;

public class SimpleProductTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config-group-buy.xml");
		GroupBuyDao dao = ctx.getBean("groupBuyDaoMySQL", GroupBuyDaoMySQL.class);
		// 新增 Product
		try {
			Product product = new Product();
			product.setProductName("Black Tea");
			product.setPrice(1000);
			product.setUnit("Box");
			product.setIsLaunch(false);
			
			dao.addProduct(product);
			System.out.println("add ok");
		} catch (Exception e) {
			System.out.println("add error: " + e.getMessage());
		}
		
	}

}
