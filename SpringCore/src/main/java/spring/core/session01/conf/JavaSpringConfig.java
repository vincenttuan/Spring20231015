package spring.core.session01.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import spring.core.session01.bean.Hello;
import spring.core.session01.bean.Lotto;

// 利用 JavaSpringConfig 來配置需管理的 java 物件(bean)
@Configuration
public class JavaSpringConfig {
	
	// 管理 Hello 這個 bean
	@Bean(name = "hello")
	@Scope("singleton") // singleton 只會產生單一物件, prototype 多物件:每一次的呼叫都會產生新的物件
	public Hello getHello() {
		Hello hello = new Hello();
		return hello;
	}
	
	@Bean
	@Scope("prototype")
    public Lotto lotto() {
        return new Lotto(); // 創建Lotto的實例
    }
	
}
