package spring.mvc.group_buy_jpa.model.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.mvc.group_buy_jpa.model.entity.Cart;
import spring.mvc.group_buy_jpa.model.entity.CartItem;
import spring.mvc.group_buy_jpa.model.entity.Product;
import spring.mvc.group_buy_jpa.model.entity.User;

@Service
public class GroupBuyDaoMySQL {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	public List<User> findAllUsers() {
	    return userRepository.findAll();
	}

	public void addUser(User user) {
	    userRepository.save(user);
	}

	public Boolean updateUserPassword(Integer userId, String newPassword) {
	    User user = userRepository.findOne(userId);
	    if(user != null) {
	        user.setPassword(newPassword);
	        userRepository.save(user);
	        return true;
	    };
	    return false;
	}

	public User findUserByUsername(String username) {
	    return userRepository.findByUsername(username);
	}

	public User findUserById(Integer userId) {
	    return userRepository.findOne(userId);
	}

	public List<Product> findAllProducts() {
	    return productRepository.findAll();
	}

	public Product findProductById(Integer productId) {
	    return productRepository.findOne(productId);
	}

	public void addProduct(Product product) {
	    productRepository.save(product);
	}

	public Boolean updateProductLaunch(Integer productId, Boolean isLaunch) {
	    Product product = productRepository.findOne(productId);
	    if(product != null) {
	        product.setIsLaunch(isLaunch);
	        productRepository.save(product);
	        return true;
	    }
	    return false;
	}
	
	public void addCart(Cart cart) {
	    cartRepository.save(cart);
	}

	public void addCartItem(CartItem cartItem) {
	    CartItem existingCartItem = cartItemRepository.findOne(cartItem.getItemId());
	    if (existingCartItem == null) {
	        cartItemRepository.save(cartItem);
	    } else {
	    	existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
	        cartItemRepository.save(existingCartItem);
	    }
	}

	public Cart findCartById(Integer cartId) {
	    return cartRepository.findOne(cartId);
	}

	public CartItem findCartItemById(Integer itemId) {
	    return cartItemRepository.findOne(itemId);
	}

	public List<Cart> findCartsByUserId(Integer userId) {
	    return cartRepository.findByUserId(userId);
	}

	public List<Cart> findNoneCheckoutCartByUserId(Integer userId) {
	    return cartRepository.findByUserIdAndIsCheckout(userId, false);
	}
	
	public Boolean checkoutCartByUserId(Integer userId) {
	    List<Cart> carts = cartRepository.findByUserIdAndIsCheckout(userId, false);
	    for (Cart cart : carts) {
	        cart.setIsCheckout(true);
	        cartRepository.save(cart);
	    }
	    return !carts.isEmpty();
	}
	
	public Boolean checkoutCartById(Integer cartId) {
		Cart cart = cartRepository.findOne(cartId);
		if(cart != null) {
	        cart.setIsCheckout(true);
	        cartRepository.save(cart);
	        return true;
	    }
		return false;
	}
	
	public Boolean removeCartItemById(Integer cartItemId) {
		cartItemRepository.delete(cartItemId);
	    return true;
	}
	
	public Boolean updateCartItemQuantity(Integer cartItemId, Integer quantity) {
	    CartItem cartItem = cartItemRepository.findOne(cartItemId);
	    if(cartItem != null) {
	        cartItem.setQuantity(quantity);
	        cartItemRepository.save(cartItem);
	        return true;
	    }
	    return false;
	}
	
	public List<Map<String, Object>> calculateTotalAmountPerUser() {
		return cartItemRepository.calculateTotalAmountPerUser();
	} 
	
}
