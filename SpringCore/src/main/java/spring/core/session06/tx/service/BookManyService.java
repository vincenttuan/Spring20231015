package spring.core.session06.tx.service;

//買/退多本書
public interface BookManyService {
	void buyMany(String username, Integer... bookIds);
	//void refundMany(String username, Integer... bookIds);
}
