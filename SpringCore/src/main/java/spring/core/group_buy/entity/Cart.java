package spring.core.group_buy.entity;

import java.util.Date;
import java.util.List;

/*
3. 購物車主檔(Master)
+--------+----------+------------+----------------+-----------+
| cartId |  userId  | isCheckout |  checkoutTime  | cartItems |
+--------+----------+------------+----------------+-----------+
|  201   |   101    |    true    | 11-19 14:52:03 |  [1, 2]   |
|  202   |   102    |    false   |                |  [3]      |
|  203   |   103    |    true    | 11-19 14:52:03 |  [4, 5]   |
|  204   |   103    |    false   |                |  []       |
|  205   |   101    |    true    | 11-19 14:52:03 |  [6]      |
+--------+----------+------------+----------------+-----------+
*/

public class Cart {
	private Integer cartId;
	private Integer userId;
	private List<CartItem> cartItems;
	private Boolean isCheckout; // 是否已經結帳
	private Date checkoutTime; // 結帳時間
	
	public Cart() {
		
	}
	
	public Cart(Integer cartId, Integer userId, List<CartItem> cartItems, Boolean isCheckout) {
		this.cartId = cartId;
		this.userId = userId;
		this.cartItems = cartItems;
		this.isCheckout = isCheckout;
		setIsCheckout(isCheckout);
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public Boolean getIsCheckout() {
		return isCheckout;
	}

	public void setIsCheckout(Boolean isCheckout) {
		if(this.isCheckout) { // 若此單已經結帳, 就不可以重新結帳
			return;
		}
		if(isCheckout) {
			this.setCheckoutTime(new Date());
		}
		this.isCheckout = isCheckout;
	}

	public Date getCheckoutTime() {
		return checkoutTime;
	}

	public void setCheckoutTime(Date checkoutTime) {
		this.checkoutTime = checkoutTime;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", userId=" + userId + ", cartItems=" + cartItems + ", isCheckout="
				+ isCheckout + ", checkoutTime=" + checkoutTime + "]";
	}
	
	
	
	
	
}
