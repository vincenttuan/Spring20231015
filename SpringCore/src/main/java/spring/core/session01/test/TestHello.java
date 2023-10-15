package spring.core.session01.test;

import spring.core.session01.bean.Hello;

public class TestHello {
	
	public static void main(String[] args) {
		// 傳統方式
		Hello hello = new Hello();
		System.out.println(hello.today());
		
		// 利用 Spring 來管理 new 物件的問題
		
		
	}
	
}
