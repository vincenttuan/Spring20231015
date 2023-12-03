package spring.mvc.session08.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.session08.bean.BookRoom;

/*
會議室預訂系統
假設您正在為一家公司開發一個會議室預訂系統。您需要實現一個控制器，該控制器可以處理會議室的預訂請求、取消請求以及查詢當前預訂狀態。

功能要求：
預訂會議室：
	路徑：/booking/bookRoom
	參數：會議室ID (roomId), 使用者名稱 (userName), 預訂時間 (time)
	返回：預訂成功或失敗的消息
	--------------------
	範例：訪問路徑：/booking/bookRoom?roomId=101&userName=John&time=2023-05-15 14:00
		將會返回預訂編號。

查看所有預訂：
	路徑：/booking/viewBookings
	返回：當前所有預訂的列表（可以簡單地返回字符串格式的預訂詳情）

取消預訂：
	路徑：/booking/cancelBooking/{bookingId}
	參數：預訂ID (bookingId)
	返回：取消成功或失敗的消息
	--------------------
	範例：訪問路徑：/booking/cancelBooking/1
		將會取消指定的預訂（以預訂編號為準）。
	
實現提示：
	您可以使用一個 List<BookRoom> bookings 或其他數據結構來模擬數據庫，存儲預訂信息。
	對於每個預訂，生成一個唯一的預訂ID。
	對於預訂和取消操作，檢查請求的有效性（如是否存在指定的會議室，是否已被預訂等）。

*/
@Controller
@RequestMapping("/booking")
public class BookingController {
	private List<BookRoom> bookings = new CopyOnWriteArrayList();
	private AtomicInteger bookingIdCounter = new AtomicInteger(0);
	
	@GetMapping(value = "/bookRoom", produces = {"text/plain;charset=utf-8"})
	@ResponseBody
	public String bookRoom(BookRoom bookRoom) {
		int bookingId = bookingIdCounter.incrementAndGet(); // 產生預定編號
		bookRoom.setBookingId(bookingId);
		bookings.add(bookRoom);
		return "預定成功, 預約編號: " + bookingId;
	}
	
	@GetMapping(value = "/viewBookings", produces = {"text/plain;charset=utf-8"})
	@ResponseBody
	private String viewBookings() {
		StringBuilder sb = new StringBuilder();
		bookings.forEach(bookRoom -> sb.append(bookRoom).append("\n"));
		return sb.toString();
	}
	
}
