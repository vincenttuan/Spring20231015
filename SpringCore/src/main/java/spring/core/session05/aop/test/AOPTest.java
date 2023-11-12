package spring.core.session05.aop.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session05.aop.Calc;
import spring.core.session05.aop.CalcImpl;

public class AOPTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config5-aop.xml");
		Calc calc = ctx.getBean("calcImpl", CalcImpl.class);
		System.out.println(calc.add(10,  20));
		System.out.println(calc.div(10,  2));
	}

}
