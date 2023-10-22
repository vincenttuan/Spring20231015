package spring.core.session02.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.core.session02.bean.Author;
import spring.core.session02.bean.Book;
import spring.core.session02.bean.Color;
import spring.core.session02.bean.Paper;
import spring.core.session02.bean.Size;

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
	
	@Bean
	public Color white() {
		Color white = new Color();
		white.setName("白");
		return white;
	}
	
	@Bean
	public Size a4() {
		Size a4 = new Size();
		a4.setName("A4");
		return a4;
	}
	
	@Bean
	public Size b5() {
		Size b5 = new Size();
		b5.setName("B5");
		return b5;
	}
	
	
	@Bean
	// 根據查找順序: byName -> byType 進行裝配
	public Paper paper1(Color white, Size a4) {
		Paper paper = new Paper();
		paper.setId(1);
		paper.setColor(white);
		paper.setSize(a4);
		return paper;
	}
	
	
	
}
