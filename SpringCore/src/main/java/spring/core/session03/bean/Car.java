package spring.core.session03.bean;

public class Car {
	private String name;
	private Integer prcie;
	
	public Car() {
		
	}

	public Car(String name, Integer prcie) {
		this.name = name;
		this.prcie = prcie;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrcie() {
		return prcie;
	}

	public void setPrcie(Integer prcie) {
		this.prcie = prcie;
	}

	@Override
	public String toString() {
		return "Car [name=" + name + ", prcie=" + prcie + "]";
	}
	
	
	
}
