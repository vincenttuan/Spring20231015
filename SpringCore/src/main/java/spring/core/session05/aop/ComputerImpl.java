package spring.core.session05.aop;

import org.springframework.stereotype.Component;

@Component
public class ComputerImpl implements Computer {

	@Override
	public Integer add2(Integer x, Integer y) {
		// 業務邏輯
		int result = x + y;
		return result;
	}

	@Override
	public Integer div2(Integer x, Integer y) {
		// 業務邏輯
		int result = x / y;
		return result;
	}
	
}
