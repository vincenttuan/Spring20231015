package spring.core.session03.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session03.bean.Clazz;
import spring.core.session03.bean.Student;
import spring.core.session03.bean.Teacher;

public class TestClazz {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config3.xml");
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
	}

}
