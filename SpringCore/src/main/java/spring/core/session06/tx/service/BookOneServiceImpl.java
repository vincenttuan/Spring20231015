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
		//propagation = Propagation.MANDATORY  // 一定要在 tx 環境下才運行	
	)
	public void buyOne(String username, Integer bookId) {
		// 寫 log
		writeToLog();
		// 查 books
		findAllBooks();
		//--------------------------------------------------
		// 1. 查詢書本價格
		Integer bookPrice = bookDao.getBookPrice(bookId);
		// 2. 減去書本庫存
		bookDao.reduceBookStock(bookId, 1); // 固定 1
		// 3. 修改客戶餘額
		bookDao.reduceWalletBalance(username, bookPrice);
		//--------------------------------------------------
		updateBookStockNested(bookId, 1);
		
	}
	
	@Transactional(
		propagation = Propagation.REQUIRES_NEW // log 自己有自己的 tx, 不受他人影響
	)
	private void writeToLog() {
		// 1. ...
		// 2. ...
		// 3. ...
	}
	
	@Transactional(
		propagation = Propagation.NOT_SUPPORTED	
	)
	public void findAllBooks() {
		//...
	}
	
	@Transactional(propagation = Propagation.NESTED)
	// 這意味著當它被一個已經存在的事務調用時，它將在一個嵌套的事務中執行。
	// 如果這個方法內部發生異常導致需要回滾，只有這個嵌套事務會被回滾，而不會影響到外部的主事務。
    public void updateBookStockNested(Integer bookId, Integer stockReduction) {
        // 這個方法將在嵌套事務中執行
        try {
            bookDao.reduceBookStock(bookId, stockReduction);
        } catch (Exception e) {
            // 處理異常，這個嵌套事務可以單獨回滾
            // ...
        }
    }

}
