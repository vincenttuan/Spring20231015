package spring.core.session03.test;

import java.util.List;

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
		
		
		
	}

}
