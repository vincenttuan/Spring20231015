package spring.core.session02.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session02.bean.Paper;
import spring.core.session02.conf.JavaSpringConfig2;

public class TestPaper {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config2.xml");
		//ApplicationContext ctx = new AnnotationConfigApplicationContext(JavaSpringConfig2.class);
		
		//Paper paper1 = ctx.getBean("paper1", Paper.class);
		//System.out.println("paper1: " + paper1);
		
		Paper paper2 = ctx.getBean("paper2", Paper.class);
		System.out.println("paper2: " + paper2);
	}

}
