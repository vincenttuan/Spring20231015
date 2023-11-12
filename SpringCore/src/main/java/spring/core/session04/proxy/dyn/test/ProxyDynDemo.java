package spring.core.session04.proxy.dyn.test;

import spring.core.session04.proxy.dyn.Calc;
import spring.core.session04.proxy.dyn.CalcImpl;
import spring.core.session04.proxy.dyn.DynProxy;

public class ProxyDynDemo {

	public static void main(String[] args) {
		// 透過動態代理來執行
		Calc calc = (Calc)new DynProxy(new CalcImpl()).getProxy();
		System.out.println(calc.add(10, 20));
		System.out.println(calc.div(10, 5));
				
		

	}

}
