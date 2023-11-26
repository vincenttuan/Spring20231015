package spring.core.group_buy.conf;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;

// GroupBuy Java 配置
@Configuration
@ComponentScan(basePackages = "spring.core.group_buy")
@PropertySource("classpath:db.properties")
public class GroupBuyConfig {
	@Value("${jdbc.driver}")
	private String driverClass;
	
	@Value("${jdbc.url}")
	private String jdbcUrl;
	
	@Value("${jdbc.username}")
	private String user;
	
	@Value("${jdbc.password}")
	private String password;
	
	@Bean
	public DataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(driverClass);
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		dataSource.setInitialPoolSize(10);
		
		dataSource.setMaxIdleTime(30);
		dataSource.setMaxPoolSize(100);
		dataSource.setMinPoolSize(10);
		dataSource.setAcquireIncrement(3);
		dataSource.setIdleConnectionTestPeriod(60);
		
		return dataSource();
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
		return new JdbcTemplate(dataSource());
	} 
	
	
}
