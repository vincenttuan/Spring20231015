package spring.core.session03.factory;

import java.util.Random;

import org.springframework.beans.factory.FactoryBean;

import spring.core.session03.bean.Car;

/*
 * FactoryBean 的使用時機:
 * 若需要對 bean 做一系列的複雜初始程序
 * 例如:
 * 1. 建立一個 bean 物件的時候, 必須要連資料庫取得相關資訊之後, 將該資訊注入到指定物件屬性中
 * 2. 建立一個 bean 物件的時候, 必須要連某一個雲端資源取得相關資訊之後, 將該資訊注入到指定物件屬性中
 * */
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
