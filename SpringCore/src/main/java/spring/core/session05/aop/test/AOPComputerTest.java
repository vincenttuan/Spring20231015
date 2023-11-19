package spring.core.session05.aop.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session05.aop.Computer;
import spring.core.session05.aop.ComputerImpl;

public class AOPComputerTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config5-aop.xml");
		Computer computer = ctx.getBean("computerImpl", ComputerImpl.class);
		System.out.println(computer.add(10, 20));
		System.out.println(computer.div(40, 20));

	}

}
