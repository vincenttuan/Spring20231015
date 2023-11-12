package spring.core.session05.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 可以用在執行時期
@Target(ElementType.METHOD) // 此註解只能用在方法上
public @interface MyLoggerAnnoation {
	
}
