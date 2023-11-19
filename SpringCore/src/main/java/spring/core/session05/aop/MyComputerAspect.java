package spring.core.session05.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(2)
public class MyComputerAspect {
	@Pointcut(value = "execution(* spring.core.session05.aop.ComputerImpl.*(..))")
	public void pt() {}
	
	// 環繞通知
	// 注意事項: 若要進行環繞通知, 建議將其他通知關閉
	@Around(value = "pt()")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		// 建立返回值變數
		Object result = null;
		try {
			// 1. 前置通知
			String methodName = proceedingJoinPoint.getSignature().getName();
			Object[] args = proceedingJoinPoint.getArgs();
			System.out.printf("環繞通知(前置通知) - 方法名稱: %s 方法參數: %s%n", methodName, Arrays.toString(args));
			
			// 代理執行業務方法 (重要)
			result = proceedingJoinPoint.proceed();
			
		} catch (Throwable e) {
			
		} finally {
			
		}
		return result;
	}
}










