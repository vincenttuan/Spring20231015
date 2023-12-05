package spring.mvc.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private String username = "user";
	private String password; // 使用者會輸入 1234
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@PostConstruct
    public void init() {
        //password = passwordEncoder().encode("1234");  // 使用者會輸入 1234
        //System.out.println(password);
		password = "$2a$10$epYnjGq7/Xss.52CWPlIrusLV6zLUQ2ve73c.52StYZzczdYR.L7m"; // 1234 的加密資料, 假設是從資料表中拿出來的
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth
	        .inMemoryAuthentication()
	        .passwordEncoder(passwordEncoder())
	        .withUser(username).password(password).roles("USER");
	        
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable() // 禁用 csrf
            .authorizeRequests()
	            .antMatchers("/mvc/login", "/mvc/logout").permitAll() // 允许所有用户访问登录页面
	            .antMatchers("/mvc/**").authenticated() // 其他 /mvc/ 下的路径需要认证
                .anyRequest().permitAll()
            .and()
            .formLogin()
                .loginPage("/mvc/login") // 自定义登录页面
                .permitAll()
            .and()
            .logout()
            	.logoutUrl("/mvc/logout") // 设置自定义登出 URL
                .permitAll();
    }

}
