package spring.core.session05.aop;

import org.springframework.stereotype.Component;

// 讓 Spring 來管理此物件
@Component
public class CalcImpl implements Calc {
	
	@MyLoggerAnnoation
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
