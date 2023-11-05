package spring.core.session03.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session03.bean.Car;

public class TestCar {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config3.xml");
		// 利用 CarFactory 來取得 Car
		Car car1 = ctx.getBean("carFactory", Car.class);
		Car car2 = ctx.getBean("carFactory", Car.class);
		Car car3 = ctx.getBean("carFactory", Car.class);
		System.out.println(car1);
		System.out.println(car2);
		System.out.println(car3);
	}

}
