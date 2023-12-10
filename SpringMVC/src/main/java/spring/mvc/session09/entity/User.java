package spring.mvc.session09.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {
	private Integer id; // 序號
	
	@NotEmpty(message = "姓名不可以是空的")
    @Size(min = 1, max = 10, message = "姓名字數必須介於 1~10 之間")
	private String name; // 姓名
	
	@NotNull(message = "年齡不可以是空的")
	@Range(min = 1, max = 150, message = "年齡必須介於 1 ~ 150 之間")
    //@Min(1)
    //@Max(100)
	private Integer age; // 年齡
	
	@NotNull(message = "生日不可以是空的")
    @Past(message = "生日必須是過去的日期")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date birth; // 生日
	
	@Size(max = 1000)
	private String resume; // 履歷
	
	@NotNull(message = "教育程度不可以是空的")
	private Integer educationId;
	
	@NotNull(message = "性別不可以是空的")
	private Integer sexId;
	
	@Size(min = 1, message = "興趣至少要選一個")
	private Integer[] interestIds;
	
	private EducationData education; // 教育程度: 1:小學, 2:國中, 3:高中, 4:大學, 5:研究所 (單選)
	private SexData sex;       // 性別: 1:男生, 2:女生 (單選)
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
