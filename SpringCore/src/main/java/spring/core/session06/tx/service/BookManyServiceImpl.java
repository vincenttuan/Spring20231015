package spring.core.session06.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookManyServiceImpl implements BookManyService {
	
	@Autowired
	private BookOneService bookOneService;
	
	@Override
	@Transactional
	public void buyMany(String username, Integer... bookIds) {
		// for-each
		for(Integer bookId : bookIds) {
			// 一次買一本(一本一本買)
			bookOneService.buyOne(username, bookId);
		}
	}

}
