package spring.core.session01.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.core.session01.bean.Lotto;

public class TestLotto {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext("beans-config1.xml");
		Lotto lotto = ctx.getBean(Lotto.class); // 只有一個 Hello <bean> 的配置才可以這樣寫
		System.out.println(lotto.getNumber());

	}

}
