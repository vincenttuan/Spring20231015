package spring.core.session06.tx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.core.session06.tx.service.BookManyService;
import spring.core.session06.tx.service.BookOneService;

@Controller
public class BookController {
	
	@Autowired
	private BookOneService bookOneService;
	
	@Autowired
	private BookManyService bookManyService;
	
	// 買單本書
	public void buyOneBook(String username, Integer bookId) {
		bookOneService.buyOne(username, bookId);
		System.out.println("buyOneBook OK");
	}
	
	// 買多本書
	public void buyManyBooks(String username, Integer... bookIds) {
		bookManyService.buyMany(username, bookIds);
		System.out.println("buyManyBooks OK");
	}
	
}
