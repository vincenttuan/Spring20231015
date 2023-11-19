package spring.core.group_buy.dao;

import java.util.List;
import java.util.Optional;

import spring.core.group_buy.entity.Cart;
import spring.core.group_buy.entity.Product;
import spring.core.group_buy.entity.User;

public interface GroupBuyDao {
	/** 
	 * 1. 查詢所有用戶
	 * @return 返回用戶列表
	 */
	List<User> findAllUsers();
	
	/** 
	 * 2. 新增用戶
	 * @param user
	 */
	void addUser(User user);
	
	/** 
	 * 3. 修改密碼
	 * @param userId
	 * @param newPassword
	 */
	void updateUserPassword(Integer userId, String newPassword);
	
	/** 
	 * 4. 根據用戶名稱查找用戶(登入用)
	 * @param username 使用者名稱
	 * @return Optional<User> 
	 */
	Optional<User> findUserByUsername(String username);
	
	/** 
	 * 5. 根據用戶ID查找用戶
	 * @param userId 使用者 ID
	 * @return Optional<User>
	 */
	Optional<User> findUserById(String userId);
	
	/** 
	 * 1. 查詢所有商品
	 * @return 返回商品列表
	 */
	List<Product> findAllProducts();
	
	/** 
	 * 2. 根據產品ID來查找商品
	 * @param productId 商品 ID
	 * @return Optional<Product>
	 */
	Optional<Product> findProductById(Integer productId);
	
	/** 3. 新增商品
	 * @param product 商品
	 */
	void addProduct(Product product);
	
	/** 
	 * 4. 變更商品上架狀態
	 * @param productId 商品 ID
	 * @param isLaunch 上架狀態(true: 上架, false: 下架) 
	 */
	void updateProductLaunch(Integer productId, Boolean isLaunch);
	
	/** 
	 * 1. 查詢所有購物車資料
	 * @return 返回所有購物車列表
	 */
	List<Cart> findAllCarts();
	
	/** 
	 * 2. 根據用戶ID來查找其所有購物車資料
	 * @param userId 使用者 ID
	 * @return 返回該用戶的所有購物車列表
	 */
	List<Cart> findCartsBuyUserId(Integer userId);
	
	/** 
	 * 3. 根據用戶ID及結帳狀態來查找其所有購物車資料
	 * @param userId 使用者 ID
	 * @param isCheckout 結帳狀態
	 * @return 返回該用戶符合條件的購物車列表
	 */
	List<Cart> findCartsByUserIdAndCheckoutStatus(Integer userId, Boolean isCheckout);
	
	/** 
	 * 4. 根據用戶ID來查找其未結帳的購物車資料
	 * @param userId 使用者 ID
	 * @return Optional<Cart> 返回找到的購物車(包含未找到也會函在其中)
	 */
	Optional<Cart> findNoneCheckoutCartByUserId(Integer userId);
	
	/** 5. 新增購物車資料
	 * @param cart 購物車
	 */
	void addCart(Cart cart);
	
	/** 
	 * 6. 根據用戶ID將該用戶的購物車設置為已結帳狀態(前台的事件)
	 * @param userId 使用者 ID
	 */
	void checkoutCartsByUserId(Integer userId);
	
	/** 
	 * 7. 根據購物車ID將購物車設置為已結帳狀態(後台的事件)
	 * @param cartId 購物車 ID
	 */
	void checkoutCartBuyId(Integer cartId);
	
	/** 
	 * 8. 根據購物車項目ID刪除指定的購物車項目
	 * @param cartItemId 購物車項目 ID
	 */
	void removeCartItemById(Integer cartItemId);
	
	/** 
	 * 9. 更新購物車項目的數量
	 * @param cartItemId 購物車項目 ID
	 * @param quantity 新的數量
	 */
	void updateCartItemQuantity(Integer cartItemId, Integer quantity);
	
}
