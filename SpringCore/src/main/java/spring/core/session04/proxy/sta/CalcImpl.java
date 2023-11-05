package spring.core.session04.proxy.sta;

public class CalcImpl implements Calc {

	@Override
	public Integer div(Integer x, Integer y) {
		int result = x / y;
		return result;
	}

}
