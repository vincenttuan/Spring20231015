package spring.core.session04.proxy.dyn.test;

import spring.core.session04.proxy.dyn.Calc;
import spring.core.session04.proxy.dyn.CalcImpl;
import spring.core.session04.proxy.dyn.DynProxy;
import spring.core.session04.proxy.sta.Man;
import spring.core.session04.proxy.sta.Person;
import spring.core.session04.proxy.sta.Woman;

public class ProxyDynDemo {

	public static void main(String[] args) {
		// 透過動態代理來執行
		Calc calc = (Calc)new DynProxy(new CalcImpl()).getProxy();
		System.out.println(calc.add(10, 20));
		System.out.println(calc.div(10, 5));
		//System.out.println(calc.div(10, 0));
				
		Person person = (Person)new DynProxy(new Woman()).getProxy();
		person.work();

	}

}
