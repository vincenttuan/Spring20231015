package spring.core.session04.proxy.sta;

public class CalcImpl implements Calc {

	@Override
	public int div(int x, int y) {
		int result = x / y;
		return result;
	}

}
