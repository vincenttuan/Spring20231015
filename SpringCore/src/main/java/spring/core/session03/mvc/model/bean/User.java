package spring.core.session03.mvc.model.bean;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component // 等於 @Component(value = "user")
public class User {
	
	@Value(value = "John") // 給予屬性預設值
	private String username; // 姓名
	
	@Value(value = "19")
	private Integer age; // 年齡
	
	/*
	 * #{} 這是一個 SpringEL 表達式
	 * ${ .. } 表示取得屬性值. Ex: ${nickname} 表示要取得/配置 nickname 的資料
	 * ${nickname: {'Baby', 'Lucky'}} 使用 "Baby", "Lucky" 作為 nickname 的預設陣列資料
	 * */
	@Value(value = "#{${nickname: {'Baby', 'Lucky'}}}")
	private String[] nickname; // 暱稱
	
	@Value(value = "#{${subjects: {'Java', 'English'}}}")
	private Set<String> subjects; // 所修的科目
	
	@Value(value = "#{${scores: {100, 90}}}")
	private List<Integer> scores; // 所修科目的成績
	
	@Value(value = "#{${hobbies: {'1':'Programming', '2':'BaseBall'}}}")
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
	public List<Integer> getScores() {
		return scores;
	}
	public void setScores(List<Integer> sores) {
		this.scores = sores;
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
				+ subjects + ", scores=" + scores + ", hobbies=" + hobbies + "]";
	}
	
	
}
