package spring.core.session03.bean;

// 資料庫連線物件
public class DBConn {
	private boolean isOpen;
	
	public void begin() { // 開啟連線(只會執行一次)
		System.out.println("開啟資料庫連線");
		isOpen = true;
	}
	
	public void create() {
		if(isOpen) {
			System.out.println("資料新增程序...");
		}
	}
	
	public void update() {
		if(isOpen) {
			System.out.println("資料修改程序...");
		}
	}
	
	public void delete() {
		if(isOpen) {
			System.out.println("資料刪除程序...");
		}
	}
	
	public void query() {
		if(isOpen) {
			System.out.println("資料查詢程序...");
		}
	}
	
	
	public void close() { // 關閉連線(只會執行一次)
		if(isOpen) {
			isOpen = false;
			System.out.println("關閉資料庫連線");
		}
	}
}
