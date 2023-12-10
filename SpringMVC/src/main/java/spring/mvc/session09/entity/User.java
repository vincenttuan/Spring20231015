package spring.mvc.session09.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {
	private Integer id; // 序號
	private String name; // 姓名
	private Integer age; // 年齡
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date birth; // 生日
	private String resume; // 履歷
	
	private Integer educationId;
	private EducationData education; // 教育程度: 1:小學, 2:國中, 3:高中, 4:大學, 5:研究所 (單選)
	
	private Integer sexId;
	private SexData sex;       // 性別: 1:男生, 2:女生 (單選)
	
	private Integer[] interestIds;
	private List<InterestData> interests; // 興趣: 1:爬山, 2:看書, 3:打球, 4:飛控, 5:手遊 (多選)
	
	// -----------------------------------------------------------------------------------
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public Integer getEducationId() {
		return educationId;
	}
	public void setEducationId(Integer educationId) {
		this.educationId = educationId;
	}
	public EducationData getEducation() {
		return education;
	}
	public void setEducation(EducationData education) {
		this.education = education;
	}
	public Integer getSexId() {
		return sexId;
	}
	public void setSexId(Integer sexId) {
		this.sexId = sexId;
	}
	public SexData getSex() {
		return sex;
	}
	public void setSex(SexData sex) {
		this.sex = sex;
	}
	public Integer[] getInterestIds() {
		return interestIds;
	}
	public void setInterestIds(Integer[] interestIds) {
		this.interestIds = interestIds;
	}
	public List<InterestData> getInterests() {
		return interests;
	}
	public void setInterests(List<InterestData> interests) {
		this.interests = interests;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", birth=" + birth + ", resume=" + resume
				+ ", educationId=" + educationId + ", education=" + education + ", sexId=" + sexId + ", sex=" + sex
				+ ", interestIds=" + Arrays.toString(interestIds) + ", interests=" + interests + "]";
	}
	
	
	
	
	
}
