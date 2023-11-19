package spring.core.group_buy.entity;

import java.util.List;

/*
3. 購物車主檔(Master)
+--------+----------+-----------+------------+
| cartId |  userId  | cartItems | isCheckout |
+--------+----------+-----------+------------+
|  201   |   101    | [1, 2]    |    true    |
|  202   |   102    | [3]       |    false   |
|  203   |   103    | [4, 5]    |    true    |
|  204   |   103    | []        |    false   |
|  205   |   101    | [6]       |    true    |
+--------+----------+-----------+------------+
*/

public class Cart {
	private Integer cartId;
	private Integer userId;
	private List<CartItem> cartItems;
	private Boolean isCheckout; // 是否已經結帳
	
	public Cart() {
		
	}
	
	public Cart(Integer cartId, Integer userId, List<CartItem> cartItems, Boolean isCheckout) {
		this.cartId = cartId;
		this.userId = userId;
		this.cartItems = cartItems;
		this.isCheckout = isCheckout;
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
		this.isCheckout = isCheckout;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", userId=" + userId + ", cartItems=" + cartItems + ", isCheckout="
				+ isCheckout + "]";
	}
	
	
	
}
