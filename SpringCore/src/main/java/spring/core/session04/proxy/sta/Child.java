package spring.core.session04.proxy.sta;

public class Child implements Person {

	@Override
	public void work() {
		// 公用邏輯
		System.out.println("出門戴口罩");
		
		System.out.println("若有例外發生: 沒口罩?");
		System.out.println("去買口罩");
		System.out.println("並將口罩帶回");
		
		// 業務邏輯
		System.out.println("Child 去學校上課");
		// 其他業務邏輯 ...
		
		// 公用邏輯
		System.out.println("回家脫口罩");
	}
	
}
