package spring.core.session05.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// 切面程式 Aspect
// 執行 Log 紀錄的相關公用邏輯
@Component // 可以被 Spring 來管理
@Aspect // 告訴 Spring 說此為一個切面程式
@Order(1) // 調用的順序(數字越小越先被執行)
public class MyLoggerAspect {
	
	// 建立一個切入點的邏輯方法
	// value = "切入點表達式"
	@Pointcut(value = "execution(public Integer spring.core.session05.aop.CalcImpl.add(..))")
	public void pt() {}
	
	@Pointcut(value = "execution(public Integer spring.core.session05.aop.CalcImpl.div(..))")
	public void pt2() {}
	
	// 前置通知(Advice)
	//@Before(value = "execution(public Integer spring.core.session05.aop.CalcImpl.add(Integer, Integer))") // 注入指定方法簽章
	//@Before(value = "execution(public Integer spring.core.session05.aop.CalcImpl.add(..))") // 注入指定方法簽章, 不限制方法參數
	//@Before(value = "execution(public Integer spring.core.session05.aop.CalcImpl.*(..))") // 不指定方法名稱, 不限制方法參數
	//@Before(value = "execution(* spring.core.session05.aop.CalcImpl.*(..))") // 不限權限, 不限回傳值, 不指定方法名稱, 不限制方法參數
	//@Before(value = "execution(* spring.core.session05.aop.*.*(..))") // 不限權限, 不限回傳值, 不限類別, 不指定方法名稱, 不限制方法參數
	//@Before(value = "execution(* *(..))") // 全部攔截, 都不限制
	@Before(value = "pt()")
	public void beforeAdvice(JoinPoint joinPoint) { // JoinPoint 連接點
		String methodName = joinPoint.getSignature().getName(); // 得到 JoinPoint 連接點的方法名稱
		Object[] args = joinPoint.getArgs(); // 得到 JoinPoint 連接點的方法參數
		System.out.printf("呼叫前置通知 - JoinPoint 連接點的方法名稱: %s 方法參數: %s%n", methodName, Arrays.toString(args));
	}
}
