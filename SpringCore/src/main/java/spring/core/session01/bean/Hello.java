package spring.core.session01.bean;

import java.util.Date;

public class Hello {
	
	public Hello() {
		System.out.println("Hello 建構子");
	}
	
	public String today() {
		return "Hello " + new Date();
	}
}
