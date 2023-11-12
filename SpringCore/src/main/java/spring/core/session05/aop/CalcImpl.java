package spring.core.session05.aop;

public class CalcImpl implements Calc {

	@Override
	public Integer add(Integer x, Integer y) {
		// 業務邏輯
		Integer result = x + y;
		return result;
	}

	@Override
	public Integer div(Integer x, Integer y) {
		// 業務邏輯
		Integer result = x / y;
		return result;
	}
	

}
