package spring.mvc.session08.controller;

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

取消預訂：
	路徑：/booking/cancelBooking/{bookingId}
	參數：預訂ID (bookingId)
	返回：取消成功或失敗的消息
	--------------------
	範例：訪問路徑：/booking/cancelBooking/1
		將會取消指定的預訂（以預訂編號為準）。

查看所有預訂：
	路徑：/booking/viewBookings
	返回：當前所有預訂的列表（可以簡單地返回字符串格式的預訂詳情）
	
實現提示：
	您可以使用一個 List<Map<String, Object>> 或其他數據結構來模擬數據庫，存儲預訂信息。
	對於每個預訂，生成一個唯一的預訂ID。
	對於預訂和取消操作，檢查請求的有效性（如是否存在指定的會議室，是否已被預訂等）。

*/
public class BookingController {

}
