package spring.core.session04.proxy;

import spring.core.session04.proxy.sta.Calc;
import spring.core.session04.proxy.sta.CalcImpl;

public class ProxyStaticCalc {

	public static void main(String[] args) {
		Calc calc = new CalcImpl();
		System.out.println(calc.div(10, 2));
		System.out.println(calc.div(10, 5));
		System.out.println(calc.div(10, 0));
	}

}
