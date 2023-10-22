package spring.core.session02.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.core.session02.bean.Author;

@Configuration
public class JavaSpringConfig2 {
	
	@Bean
	public Author author1() {
		return new Author();
	}
	
	@Bean
	public Author author2() {
		Author author = new Author();
		author.setName("John");
		author.setSex('男');
		author.setAge(29);
		return author;
	}
	
	@Bean
	public Author author3() {
		return new Author("Mary", '女', 20);
	}
	
}
