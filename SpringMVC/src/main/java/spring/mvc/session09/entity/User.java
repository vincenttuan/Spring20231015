package spring.mvc.session09.entity;

import java.util.Arrays;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {
	private String name; // 姓名
	private Integer age; // 年齡
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date birth; // 生日
	
	private String education; // 教育程度: 小學, 國中, 高中, 大學, 研究所 (單選)
	private String sex;       // 性別: 男生, 女生 (單選)
	private String[] interests; // 興趣: 爬山, 看書, 打球, 飛控, 手遊 (多選)
	private String resume; // 履歷
	
	public User() {
		
	}
	
	public User(String name, Integer age, Date birth, String education, String sex, String[] interests, String resume) {
		this.name = name;
		this.age = age;
		this.birth = birth;
		this.education = education;
		this.sex = sex;
		this.interests = interests;
		this.resume = resume;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String[] getInterests() {
		return interests;
	}

	public void setInterests(String[] interests) {
		this.interests = interests;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", birth=" + birth + ", education=" + education + ", sex=" + sex
				+ ", interests=" + Arrays.toString(interests) + ", resume=" + resume + "]";
	}
	
	
	
}
