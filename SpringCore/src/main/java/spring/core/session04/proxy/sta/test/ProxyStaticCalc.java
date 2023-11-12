package spring.core.session04.proxy.sta.test;

import spring.core.session04.proxy.sta.Calc;
import spring.core.session04.proxy.sta.CalcImpl;
import spring.core.session04.proxy.sta.CalcProxy;

public class ProxyStaticCalc {

	public static void main(String[] args) {
		//Calc calc = new CalcImpl();
		//Calc calc = new CalcProxy(new CalcImpl());
		
		Calc calc = new CalcProxy((x, y) -> x / y);
		System.out.println(calc.div(10, 2));
		System.out.println(calc.div(10, 5));
		System.out.println(calc.div(10, 0));
	}

}
