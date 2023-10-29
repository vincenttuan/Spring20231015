package spring.core.session03.test;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.tomcat.util.net.openssl.OpenSSLUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session03.bean.Clazz;
import spring.core.session03.bean.Student;
import spring.core.session03.bean.Teacher;
import spring.core.session03.conf.JavaSpringConfig3;

public class TestClazz {

	public static void main(String[] args) {
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config3.xml");
		ApplicationContext ctx = new AnnotationConfigApplicationContext(JavaSpringConfig3.class);
		// 課程
		Clazz clazz1 = ctx.getBean("clazz1", Clazz.class);
		System.out.println(clazz1);
		// 學生
		Student student1 = ctx.getBean("student1", Student.class);
		Student student2 = ctx.getBean("student2", Student.class);
		System.out.println(student1);
		System.out.println(student2);
		// 講師
		Teacher teacher1 = ctx.getBean("teacher1", Teacher.class);
		System.out.println(teacher1);
		System.out.println(teacher1.getStudents());
		System.out.println(teacher1.getSubjects());
		System.out.println(teacher1.getSalary());
		
		Teacher teacher2 = ctx.getBean("teacher2", Teacher.class);
		System.out.println(teacher2);
		System.out.println(teacher2.getStudents());
		System.out.println(teacher2.getSubjects());
		System.out.println(teacher2.getSalary());
		
		List<Student> students = ctx.getBean("students", List.class);
		System.out.println(students);
		// 請問每一個學生個別共修了多少學分 ?
		// 印出範例如下:
		// John 修了 6 學分
		// Mary 修了 4 學分
		students.forEach(student -> {
			String name = student.getName();
			int total = student.getClazzs().stream().mapToInt(Clazz::getCredit).sum();
			System.out.printf("%s 修了 %d 學分\n", name, total);
		});
		
		// 將 每一個學生個別共修了多少學分 收集成為一個 Map 集合 
		// 例如: {John=6, Mary=4 ... }
		Map<String, Integer> studentCredits = students.stream()
				.collect(Collectors.toMap(
						Student::getName, // key: 學生的名字 
						student -> student.getClazzs().stream().mapToInt(Clazz::getCredit).sum() // value: 學生總學分
				));
		System.out.println(studentCredits);
		
		IntSummaryStatistics stat = studentCredits.values().stream()
				.mapToInt(Integer::intValue)  // Integer 轉 int
				.summaryStatistics();
		System.out.println(stat);
		System.out.printf("平均每個學生的學分數: %.1f%n", stat.getAverage());
	}

}
