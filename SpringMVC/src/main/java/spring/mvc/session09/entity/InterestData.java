package spring.mvc.session09.entity;

public class InterestData {
	private String id;
	private String name;
	
	public InterestData() {
		
	}
	
	public InterestData(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "InterestData [id=" + id + ", name=" + name + "]";
	}
	
}
