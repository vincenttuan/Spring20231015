package spring.core.group_buy.dao;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import spring.core.group_buy.entity.Cart;
import spring.core.group_buy.entity.CartItem;
import spring.core.group_buy.entity.Product;
import spring.core.group_buy.entity.User;

// In-Memory
public class GroupBuyDaoInMemory implements GroupBuyDao {
	
	// In-Memory table
	private static List<User> users = new CopyOnWriteArrayList<>();
	private static List<Product> products = new CopyOnWriteArrayList<>();
	private static List<Cart> carts = new CopyOnWriteArrayList<>();
	private static List<CartItem> cartItems = new CopyOnWriteArrayList<>();
	
	@Override
	public List<User> findAllUsers() {
		return users;
	}

	@Override
	public void addUser(User user) {
		users.add(user);
	}

	@Override
	public void updateUserPassword(Integer userId, String newPassword) {
		users.stream().filter(user -> user.getUserId().equals(userId)) // 根據 userId 查找 user
					  .findAny() // 找尋任何一個符合的
					  .ifPresent(user -> user.setPassword(newPassword)); // 若有找到就修改 password
	}

	@Override
	public Optional<User> findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<User> findUserById(String userId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Product> findAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Product> findProductById(Integer productId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProductLaunch(Integer productId, Boolean isLaunch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Cart> findAllCarts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cart> findCartsBuyUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cart> findCartsByUserIdAndCheckoutStatus(Integer userId, Boolean isCheckout) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Cart> findNoneCheckoutCartByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void addCart(Cart cart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkoutCartsByUserId(Integer userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkoutCartBuyId(Integer cartId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCartItemById(Integer cartItemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCartItemQuantity(Integer cartItemId, Integer quantity) {
		// TODO Auto-generated method stub
		
	}
	
}
