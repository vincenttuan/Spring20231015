package spring.core.session06.tx.service;

// 買/退單本書
public interface BookOneService {
	void buyOne(String username, Integer bookId);
	//void refundOne(String username, Integer bookId);
}
