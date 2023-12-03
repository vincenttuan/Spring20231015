package spring.mvc.session08.bean;

public class User {
	// user?name=John&age=18&score=80&pass=true
	private String name;
	private Integer age;
	private Double score;
	private Boolean pass;
	
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
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Boolean getPass() {
		return pass;
	}
	public void setPass(Boolean pass) {
		this.pass = pass;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", score=" + score + ", pass=" + pass + "]";
	}
	
}
