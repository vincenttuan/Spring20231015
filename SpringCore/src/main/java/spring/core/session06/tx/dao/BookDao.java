package spring.core.session06.tx.dao;

public interface BookDao {
	// 交易服務
	Integer getBookPrice(Integer bookId); // 取得書本價格
	Integer getBookStock(Integer bookID); // 取得書本庫存
	Integer getWalletBalance(String username); // 取得客戶目前餘額
	
	Integer reduceBookStock(Integer bookId, int reduce); // 更新書本庫存(reduce 減量)
	//Integer incrementBookStock(Integer bookId, int increment); // 更新書本庫存(increment 增量)
	
	Integer reduceWalletBalance(String username, int reduce); // 更新錢包餘額(reduce 減量)
	//Integer incrementWalletBalance(String username, int increment); // 更新錢包餘額(increment 增量)
	
}
