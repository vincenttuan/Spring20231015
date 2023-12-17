package spring.core.session06.tx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import spring.core.session06.tx.service.BookManyService;
import spring.core.session06.tx.service.BookOneService;

@Controller
public class BookController {
	
	@Autowired
	private BookOneService bookOneService;
	
	@Autowired
	private BookManyService bookManyService;
	
	public void buyOneBook(String username, Integer bookId) {
		bookOneService.buyOne(username, bookId);
		System.out.println("buyOneBook OK");
	}
	
	public void buyManyBooks(String username, Integer... bookIds) {
		bookManyService.buyMany(username, bookIds);
		System.out.println("buyManyBook OK");
	}
	
}
