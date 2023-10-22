package spring.core.session01.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session01.bean.Lotto;
import spring.core.session01.conf.JavaSpringConfig;

public class TestLotto {

	public static void main(String[] args) {
		// XML 配置
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config1.xml");
		Lotto lotto = ctx.getBean(Lotto.class); // 只有一個 Hello <bean> 的配置才可以這樣寫
		System.out.println(lotto.getNumber());
		
		Lotto lotto2 = ctx.getBean("lotto", Lotto.class);
		System.out.println(lotto2.getNumber());
		
		// Java 配置 (Homework)
		ApplicationContext javaCtx = new AnnotationConfigApplicationContext(JavaSpringConfig.class);
        Lotto lottoJavaConfig = javaCtx.getBean(Lotto.class);
        System.out.println(lottoJavaConfig.getNumber());

        Lotto lottoJavaConfig2 = javaCtx.getBean("lotto", Lotto.class);
        System.out.println(lottoJavaConfig2.getNumber());
	}

}
