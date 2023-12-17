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
		updateBookStockNested(bookId, 1);
		//--------------------------------------------------
		// 1. 查詢書本價格
		Integer bookPrice = bookDao.getBookPrice(bookId);
		// 2. 減去書本庫存
		bookDao.reduceBookStock(bookId, 1); // 固定 1
		// 3. 修改客戶餘額
		bookDao.reduceWalletBalance(username, bookPrice);
		//--------------------------------------------------
		
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
	
	// 這意味著當它被一個已經存在的事務調用時，它將在一個嵌套的事務中執行。
	// 如果這個方法內部發生異常導致需要回滾，只有這個嵌套事務會被回滾，而不會影響到外部的主事務。
	// 若當前沒有事務行為就像 REQUIRED 一樣
	// 嵌套事務不會影響主事務
	// 但主事務會影響嵌套事務
	@Transactional(propagation = Propagation.NESTED)
	public void updateBookStockNested(Integer bookId, Integer stockReduction) {
        // 這個方法將在嵌套事務中執行
        bookDao.reduceBookStock(bookId, stockReduction);
        /*
        這邊發生錯誤是否會影響主事務 ?
        如果有一個外部事務正在運行:
			在嵌套事務中發生的錯誤會導致該嵌套事務回滾。
			但這種回滾僅限於嵌套事務本身，不會影響外部（主）事務的其餘部分。
			外部事務可以繼續進行，並根據自身邏輯決定是否提交或回滾。
			
		如果沒有外部事務正在運行:
			由於 NESTED 在沒有現有事務時的行為類似於 REQUIRED，一個新的事務將被創建。
			在這種情況下，updateBookStockNested 方法中的錯誤會導致整個事務回滾。
		
		總結來說，當使用 NESTED 時，嵌套事務中的錯誤對主事務的影響取決於是否存在一個活躍的外部事務。
		在嵌套事務中的錯誤不會直接導致外部事務回滾，除非在處理這個錯誤的過程中明確指示了外部事務也應該回滾。
        */
    }

}
