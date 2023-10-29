package spring.core.session03.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session03.bean.DBConn;
import spring.core.session03.conf.JavaSpringConfig3;

public class TestConn {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config3.xml");
		//AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaSpringConfig3.class);
		
		DBConn dbConn = ctx.getBean("dbConn", DBConn.class);
		
		dbConn.create();
		dbConn.create();
		dbConn.update();
		dbConn.delete();
		dbConn.query();
		
		ctx.close();
	}
}
