package spring.core.session01.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session01.bean.Hello;
import spring.core.session01.bean.Lotto;

public class TestBeanScpre {

	public static void main(String[] args) {
		// 觀察 scope 對於系統初始化有何差別
		// 對於 scope="singleton" (預設) 系統在載入設定檔的時候, 會先建立該物件
		// 對於 scope="prototype" 系統在載入設定檔的時候, 不會先建立該物件, 而是等到程式碼中有使用的時候才會動態創建
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config1.xml");
		//Hello hello1 = ctx.getBean(Hello.class);
		//Hello hello2 = ctx.getBean(Hello.class);
		//Lotto lotto1 = ctx.getBean(Lotto.class);
		//Lotto lotto2 = ctx.getBean(Lotto.class);
		
	}

}
