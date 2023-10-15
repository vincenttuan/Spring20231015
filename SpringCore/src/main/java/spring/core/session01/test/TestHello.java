package spring.core.session01.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.core.session01.bean.Hello;
import spring.core.session01.conf.JavaSpringConfig;

// 執行時請加入 VM 參數: --add-opens java.base/java.lang=ALL-UNNAMED
public class TestHello {
	
	public static void main(String[] args) {
		// 傳統方式
		Hello hello = new Hello();
		System.out.println(hello.today());
		
		// 利用 Spring 來管理 new 物件的問題
		// 使用 JavaSpringConfig (Java 配置) 來取得 bean 物件
		// 1. 得到應用程式配置環境
		ApplicationContext ctx = new AnnotationConfigApplicationContext(JavaSpringConfig.class);
		// 2. 取 bean
		Hello hello2 = ctx.getBean("hello", Hello.class);
		System.out.println(hello2.today());
		
	}
	
}
