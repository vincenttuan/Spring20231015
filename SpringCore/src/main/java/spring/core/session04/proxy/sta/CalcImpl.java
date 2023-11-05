package spring.core.session04.proxy.sta;

public class CalcImpl implements Calc {

	@Override
	public Integer div(Integer x, Integer y) {
		if(y == 0) {
			System.out.println("分母不可 = 0");
			return null;
		}
		int result = x / y;
		return result;
	}

}
