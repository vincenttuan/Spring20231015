package spring.mvc.session08.bean;

public class BookRoom {
	// roomId=101&userName=John&time=2023-05-15 14:00
	private Integer bookingId;
	private Integer roomId;
	private String userName;
	private String time;
	
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "BookRoom [bookingId=" + bookingId + ", roomId=" + roomId + ", userName=" + userName + ", time=" + time
				+ "]";
	}
	
	
}
