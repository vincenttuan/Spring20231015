package spring.core.session03.mvc.model.bean;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component // 等於 @Component(value = "user")
public class User {
	private String username; // 姓名
	
	private Integer age; // 年齡
	
	private String[] nickname; // 暱稱
	
	private Set<String> subjects; // 所修的科目
	
	private List<Integer> scires; // 所修科目的成績
	
	private Map<String, String> hobbies; // 興趣
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String[] getNickname() {
		return nickname;
	}
	public void setNickname(String[] nickname) {
		this.nickname = nickname;
	}
	public Set<String> getSubjects() {
		return subjects;
	}
	public void setSubjects(Set<String> subjects) {
		this.subjects = subjects;
	}
	public List<Integer> getScires() {
		return scires;
	}
	public void setScires(List<Integer> scires) {
		this.scires = scires;
	}
	public Map<String, String> getHobbies() {
		return hobbies;
	}
	public void setHobbies(Map<String, String> hobbies) {
		this.hobbies = hobbies;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", age=" + age + ", nickname=" + Arrays.toString(nickname) + ", subjects="
				+ subjects + ", scires=" + scires + ", hobbies=" + hobbies + "]";
	}
	
	
}
