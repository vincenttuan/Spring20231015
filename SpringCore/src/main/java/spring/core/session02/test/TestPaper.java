package spring.core.session02.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session02.bean.Paper;
import spring.core.session02.conf.JavaSpringConfig2;

public class TestPaper {

	public static void main(String[] args) {
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config2.xml"); // xml 配置
		ApplicationContext ctx = new AnnotationConfigApplicationContext(JavaSpringConfig2.class); // Java 配置
		
		Paper paper1 = ctx.getBean("paper1", Paper.class); // 若使用 xml 配置該行要拿掉, 因為會受到 byType 的限制
		System.out.println("paper1: " + paper1); // 若使用 xml 配置該行要拿掉, 因為會受到 byType 的限制
		
		Paper paper2 = ctx.getBean("paper2", Paper.class);
		System.out.println("paper2: " + paper2);
		
		Paper paper3 = ctx.getBean("paper3", Paper.class);
		System.out.println("paper3: " + paper3);
	}

}
