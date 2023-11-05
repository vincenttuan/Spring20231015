package spring.core.session04.proxy.sta;

public class Woman implements Person {

	@Override
	public void work() {
		// 業務邏輯
		System.out.println("Woman 去市場買菜");
		// 其他業務邏輯 ...		
	}
	
}
