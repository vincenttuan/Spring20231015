package spring.core.session04.proxy.sta;

public class Man implements Person {

	@Override
	public void work() {
		// 業務邏輯
		System.out.println("Man 上班工作");
		// 模擬執行過程中發生例外 ...
		throw new RuntimeException("口罩突然不見了");
		// 其他業務邏輯 ...
	}
	
}
