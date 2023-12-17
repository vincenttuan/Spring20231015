package spring.core.session06.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.core.session06.tx.dao.BookDao;

@Service
public class BookOneServiceImpl implements BookOneService {
	
	@Autowired
	private BookDao bookDao;
	
	@Override
	@Transactional(
		propagation = Propagation.REQUIRED // 預設: 若當前有 tx, 則繼續使用, 反之則建立一個 tx
	)
	public void buyOne(String username, Integer bookId) {
		// 寫 log
		writeToLog();
		// 1. 查詢書本價格
		Integer bookPrice = bookDao.getBookPrice(bookId);
		// 2. 減去書本庫存
		bookDao.reduceBookStock(bookId, 1); // 固定 1
		// 3. 修改客戶餘額
		bookDao.reduceWalletBalance(username, bookPrice);
	}
	
	@Transactional(
		propagation = Propagation.REQUIRES_NEW // log 自己有自己的 tx, 不受他人影響
	)
	private void writeToLog() {
		// 1. ...
		// 2. ...
		// 3. ...
	}

}
