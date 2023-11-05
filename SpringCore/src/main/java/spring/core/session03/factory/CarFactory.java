package spring.core.session03.factory;

import java.util.Random;

import org.springframework.beans.factory.FactoryBean;

import spring.core.session03.bean.Car;

public class CarFactory implements FactoryBean<Car> {

	@Override
	public Car getObject() throws Exception {
		//Car car = new Car(); // 空的(無設定屬性資料) Car 物件
		Random random = new Random();
		int price = random.nextInt(200_0000);
		Car car = new Car("BMW", price);
		return car;
	}

	@Override
	public Class<?> getObjectType() {
		return Car.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	
}
