package spring.core.session06.tx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Integer getBookPrice(Integer bookId) { // 取得書本價格
		String sql = "select book_price from book where book_id = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, bookId);
	}

	@Override
	public Integer getBookStock(Integer bookId) { // 取得書本庫存
		String sql = "select book_amount from stock where book_id = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, bookId);
	}

	@Override
	public Integer getWalletBalance(String username) { // 取得客戶餘額
		String sql = "select balance from wallet where useranem = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, username);
	}

	@Override
	public Integer reduceBookStock(Integer bookId, Integer reduce) { // 減少書本庫存
		// 1. 檢查庫存
		Integer bookStock = getBookStock(bookId);
		if(bookStock < reduce) { // 庫存不足
			throw new RuntimeException(String.format("book_id: %d 庫存不足 (%d < %d)%n", bookId, bookStock, reduce));
		}
		// 2. 修改/更新書本庫存 (目前庫存量 - reduce)
		String sql = "update stock set book_amount = book_amount - ? where book_id = ?";
		return jdbcTemplate.update(sql, reduce, bookId);
	}

	@Override
	public Integer reduceWalletBalance(String username, Integer bookPrice) { // 減少客戶餘額
		// 1. 檢查客戶餘額
		Integer balance = getWalletBalance(username);
		
		return null;
	}
	
}
