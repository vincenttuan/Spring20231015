package spring.core.session02.test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.averagingInt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session02.bean.Author;
import spring.core.session02.conf.JavaSpringConfig2;

public class TestAuthor {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config2.xml");
		//ApplicationContext ctx = new AnnotationConfigApplicationContext(JavaSpringConfig2.class);
		Author author1 = ctx.getBean("author1", Author.class);
		System.out.println(author1);
		// 手動注入
		author1.setName("Bob");
		author1.setSex('男');
		author1.setAge(25);
		System.out.println(author1);
		
		Author author2 = ctx.getBean("author2", Author.class);
		System.out.println(author2); // 透過設定檔方法注入自動注入資料

		Author author3 = ctx.getBean("author3", Author.class);
		System.out.println(author3); // 透過設定檔建構子注入自動注入資料

		Author author4 = ctx.getBean("author4", Author.class);
		System.out.println(author4); // 透過設定檔(p)方法注入自動注入資料
		
		Author author5 = ctx.getBean("author5", Author.class);
		System.out.println(author5); // 透過設定檔(c)方法注入自動注入資料
		
		// 進行資料分析
		List<Author> authors = List.of(author1, author2, author3, author4, author5);
		System.out.println(authors);
		
		// 計算平均年齡
		double avgOfAge = authors.stream()
								 .mapToInt(Author::getAge) //.mapToInt(author -> author.getAge())
								 .average()
								 .orElse(0.0);
		System.out.printf("平均年齡: %.1f%n", avgOfAge);
		
		// 計算男女的平均年齡
		// {男 = ?, 女 = ?} 分組/groupingBy/Map
		Map<Character, Double> avgAgeBySex = authors.stream()
												 .collect(groupingBy(
														 	Author::getSex, // 分組依據性別
														 	averagingInt(Author::getAge) // 計算每一組的平均年齡
														 ));
		
		System.out.println(avgAgeBySex);
		avgAgeBySex.forEach((sex, age) -> { // key = sex, value = age
			System.out.printf("性別: %c, 平均年齡: %.1f%n", sex, age);
		});		
		
		
	}

}
