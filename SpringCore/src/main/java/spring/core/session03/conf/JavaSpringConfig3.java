package spring.core.session03.conf;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import spring.core.session03.bean.Clazz;
import spring.core.session03.bean.DBConn;
import spring.core.session03.bean.Student;
import spring.core.session03.bean.Teacher;
import spring.core.session03.factory.CarFactory;

@Configuration
@PropertySource("classpath:data.properties")
public class JavaSpringConfig3 {
	
	// 從 data.properties 中透過 @Value 注入資料
	@Value("${clazz.id}")
	private Integer clazzId;
	
	@Value("${clazz.name}")
	private String clazzName;
	
	@Value("${clazz.credit}")
	private Integer clazzCredit;
	
	@Bean(name = "clazz1") // 若 class 與 name 同名, 可以省去 name 的屬性設定
	public Clazz clazz1() {
		Clazz clazz = new Clazz();
		clazz.setId(clazzId);
		clazz.setName(clazzName);
		clazz.setCredit(clazzCredit);
		return clazz;
	}
	
	@Bean
	public Clazz clazz2() {
		Clazz clazz = new Clazz();
		clazz.setId(2);
		clazz.setName("English");
		clazz.setCredit(2);
		return clazz;
	}
	
	@Bean
	public Clazz clazz3() {
		Clazz clazz = new Clazz();
		clazz.setId(3);
		clazz.setName("Math");
		clazz.setCredit(1);
		return clazz;
	}
	
	@Bean
	public Student student1() {
		HashSet<Clazz> clazzs = new HashSet<>();
		clazzs.add(clazz1());
		clazzs.add(clazz2());
		clazzs.add(clazz3());
		Student student = new Student(1, "John");
		student.setClazzs(clazzs);
		return student;
	}
	
	@Bean
	public Student student2() {
		HashSet<Clazz> clazzs = new HashSet<>();
		clazzs.add(clazz1());
		clazzs.add(clazz3());
		Student student = new Student(2, "Mary");
		student.setClazzs(clazzs);
		return student;
	}
	
	@Bean
	public Teacher teacher1() {
		HashSet<Student> students = new HashSet<>();
		students.add(student1());
		students.add(student2());
		List<String> subjects = Arrays.asList("Program", "English");
		Map<String, Integer> salary = new HashMap<>();
		salary.put("base", 50000);
		salary.put("addition", 20000);
		
		Teacher teacher = new Teacher(1, "Bob");
		teacher.setStudents(students);
		teacher.setSubjects(subjects);
		teacher.setSalary(salary);
		
		return teacher;
	}
	
	@Bean
	public Set<Student> students() {
		return new HashSet<>(Arrays.asList(student1(), student2()));
	}
	
	@Bean List<String> subjects() {
		return Arrays.asList("Program", "English", "Math", "Music");
	}
	
	@Bean Map<String, Integer> salary() {
		Map<String, Integer> salaryMap = new HashMap<>();
		salaryMap.put("base", 65000);
		salaryMap.put("addition", 22000);
		return salaryMap;
	}
	
	@Bean
	public Teacher teacher2() {
		Teacher teacher = new Teacher();
		teacher.setId(2);
		teacher.setName("Helen");
		teacher.setStudents(students());
		teacher.setSubjects(subjects());
		teacher.setSalary(salary());
		return teacher;
	}
	
	@Bean(initMethod = "begin", destroyMethod = "close")
	public DBConn dbConn() {
		return new DBConn();
	}
	
	@Bean
	@Scope("singleton") // @Scope("prototype")
	public CarFactory carFactory() {
		return new CarFactory();
	}
	
}
