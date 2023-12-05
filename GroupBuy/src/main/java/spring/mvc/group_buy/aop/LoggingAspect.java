package spring.mvc.group_buy.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // 定义一个切点，匹配 group_buy.controller 包下所有类的所有方法
    @Before("execution(* spring.mvc.group_buy.controller.*.*(..))")
    public void logMethodParams(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("调用方法: " + methodName + "  参数: ");
        for (int i = 0; i < args.length; i++) {
            System.out.println("  参数[" + i + "]: " + args[i]);
        }
    }
}
