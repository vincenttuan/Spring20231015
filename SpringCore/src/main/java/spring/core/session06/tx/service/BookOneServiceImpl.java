package spring.core.session06.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.core.session06.tx.dao.BookDao;

@Repository
public class BookOneServiceImpl implements BookOneService {
	
	@Autowired
	private BookDao bookDao;
	
	@Override
	public void buyOne(String username, Integer bookId) {
		// 1. 查詢書本價格
		Integer bookPrice = bookDao.getBookPrice(bookId);
		// 2. 減去書本庫存
		bookDao.reduceBookStock(bookId, 1); // 固定 1
		// 3. 修改客戶餘額
		bookDao.reduceWalletBalance(username, bookPrice);
	}

}
