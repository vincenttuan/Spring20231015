package spring.core.session04.proxy;

import spring.core.session04.proxy.sta.Child;
import spring.core.session04.proxy.sta.Man;
import spring.core.session04.proxy.sta.Person;
import spring.core.session04.proxy.sta.PersonProxy;
import spring.core.session04.proxy.sta.Woman;

public class ProxyStaticDemo {

	public static void main(String[] args) {
		// 不透過代理直接創建
		/*
		Person man = new Man();
		Person woman = new Woman();
		Person child = new Child();
		*/
		
		// 透過代理間接創建
		Person man = new PersonProxy(new Man());
		Person woman = new PersonProxy(new Woman());
		Person child = new PersonProxy(new Child());
		
		man.work();
		woman.work();
		child.work();
		

	}

}
