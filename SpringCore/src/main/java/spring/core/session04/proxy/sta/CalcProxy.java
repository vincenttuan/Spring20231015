package spring.core.session04.proxy.sta;

public class CalcProxy implements Calc {
	
	private Calc calc;
	
	public CalcProxy(Calc calc) {
		this.calc = calc;
	}
	
	@Override
	public Integer div(Integer x, Integer y) {
		if(y == 0) {
			System.out.println("分母不可 = 0");
			return null;
		}
		return calc.div(x, y);
	}
	
}
