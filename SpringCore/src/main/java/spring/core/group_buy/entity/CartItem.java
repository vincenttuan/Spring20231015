package spring.core.group_buy.entity;

/*
4. 購物車明細檔(Detail)
+--------+----------+-----------+------------+
| itemId |  carId   | productId |  quantity  |
+--------+----------+-----------+------------+
|   1    |   201    |    501    |     10     |
|   2    |   201    |    502    |     8      |
|   3    |   202    |    503    |     5      |
|   4    |   203    |    502    |     8      |
|   5    |   203    |    504    |     20     |
|   6    |   205    |    505    |     15     |
+--------+----------+-----------+------------+

*/
public class CartItem {
	
	private Integer itemId;
	private Integer cartId;
	private Integer productId;
	private Integer quantity;
	
	public CartItem() {
		
	}
	
	public CartItem(Integer itemId, Integer cartId, Integer productId, Integer quantity) {
		this.itemId = itemId;
		this.cartId = cartId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartItem [itemId=" + itemId + ", cartId=" + cartId + ", productId=" + productId + ", quantity="
				+ quantity + "]";
	}
	
	
	
}
