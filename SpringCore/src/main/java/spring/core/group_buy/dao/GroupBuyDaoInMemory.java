package spring.core.group_buy.dao;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.stream.Collectors.toList;

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
	public Boolean updateUserPassword(Integer userId, String newPassword) {
		Optional<User> userOpt = users.stream().filter(user -> user.getUserId().equals(userId)) // 根據 userId 查找 user
					  .findAny(); // 找尋任何一個符合的
		
		if(userOpt.isPresent()) {
			userOpt.get().setPassword(newPassword); // 若有找到就修改 password
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Optional<User> findUserByUsername(String username) {
		return users.stream().filter(user -> user.getUsername().equals(username)).findAny();
	}

	@Override
	public Optional<User> findUserById(Integer userId) {
		return users.stream().filter(user -> user.getUserId().equals(userId)).findAny();
		//return users.stream().filter(user -> user.getUserId().intValue() == userId.intValue()).findAny();
	}

	@Override
	public List<Product> findAllProducts() {
		return products;
	}

	@Override
	public Optional<Product> findProductById(Integer productId) {
		return products.stream().filter(product -> product.getProductId().equals(productId)).findAny();
	}

	@Override
	public void addProduct(Product product) {
		products.add(product);
	}

	@Override
	public Boolean updateProductLaunch(Integer productId, Boolean isLaunch) {
		Optional<Product> productOpt = products.stream()
				.filter(product -> product.getProductId().equals(productId))
				.findAny();
		
		if(productOpt.isPresent()) {
			productOpt.get().setIsLaunch(isLaunch);
			return true; // 成功找到並更新
		} else {
			return false;
		}
	}

	@Override
	public List<Cart> findAllCarts() {
		return carts;
	}

	@Override
	public List<Cart> findCartsBuyUserId(Integer userId) {
		return carts.stream()
					.filter(cart -> cart.getUserId().equals(userId))
					.collect(toList());
	}

	@Override
	public List<Cart> findCartsByUserIdAndCheckoutStatus(Integer userId, Boolean isCheckout) {
		/*
		return carts.stream()
					.filter(cart -> cart.getUserId().equals(userId))
					.filter(cart -> cart.getIsCheckout().equals(isCheckout))
					.collect(toList());
		*/
		return carts.stream()
				.filter(cart -> cart.getUserId().equals(userId) && cart.getIsCheckout().equals(isCheckout))
				.collect(toList());
	
	}

	@Override
	public Optional<Cart> findNoneCheckoutCartByUserId(Integer userId) {
		return carts.stream()
					.filter(cart -> cart.getUserId().equals(userId))
					.filter(cart -> cart.getIsCheckout() == null || !cart.getIsCheckout())
					.findAny();
	}
	
	@Override
	public void addCart(Cart cart) {
		carts.add(cart);
	}

	@Override
	public Boolean checkoutCartsByUserId(Integer userId) {
		// 先找到該使用者目前尚未結帳的購物車
		Optional<Cart> optCart = findNoneCheckoutCartByUserId(userId);
		if(optCart.isPresent()) {
			// 若有就進行結帳
			optCart.get().setIsCheckout(true); // 結帳
			return true;
		} else {
			return false;
		}
		
		
	}

	@Override
	public Boolean checkoutCartBuyId(Integer cartId) {
		Optional<Cart> cartOpt = carts.stream()
			 .filter(cart -> cart.getCartId().equals(cartId))
			 .findAny();
		if(cartOpt.isPresent()) {
			cartOpt.get().setIsCheckout(true); // 結帳
			return true;
		} else {
			return false;
		}	 
	}

	@Override
	public Boolean removeCartItemById(Integer cartItemId) {
		Optional<CartItem> cartItemOpt = cartItems.stream()
				 .filter(item -> item.getItemId().equals(cartItemId))
				 .findAny();
		
		if(cartItemOpt.isPresent()) {
			cartItems.remove(cartItemOpt.get());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean updateCartItemQuantity(Integer cartItemId, Integer quantity) {
		Optional<CartItem> cartItemOpt = cartItems.stream()
				 .filter(item -> item.getItemId().equals(cartItemId))
				 .findAny();
		if(cartItemOpt.isPresent()) {
			cartItemOpt.get().setQuantity(quantity);
			return true;
		} else {
			return false;
		}
	}
	
}
