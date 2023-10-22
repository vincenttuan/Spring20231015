package spring.core.session02.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.core.session02.bean.Author;
import spring.core.session02.bean.Book;

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
	
	@Bean
	public Author author4() {
		Author author = new Author();
		author.setName("Helen");
		author.setSex('女');
		author.setAge(23);
		return author;
	}
	
	@Bean
	public Author author5() {
		return new Author("Tom", '男', 31);
	}
	
	@Bean
	public Book book1() {
		return new Book();
	}
	
	@Bean
	public Book book2() {
		Book book = new Book();
		book.setName("Java");
		book.setPrice(500);
		book.setAuthor(author2());
		return book;
	}
	
	@Bean
	public Book book3() {
		return new Book("Spring", 600, author3());
	}
	
	@Bean
	public Book book4() {
		Book book = new Book();
		book.setName("Python");
		book.setPrice(450);
		book.setAuthor(author4());
		return book;
	}
	
	@Bean
	public Book book5() {
		return new Book("VB", 280, author5());
	}
	
	
}
