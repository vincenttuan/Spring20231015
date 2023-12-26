package spring.mvc.group_buy_jpa.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.mvc.group_buy_jpa.model.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByCartId(Integer cartId);
    
    @Query("SELECT new map(c.userId as userId, SUM(p.price * ci.quantity) as total) " +
    	       "FROM CartItem ci " +
    	       "JOIN ci.cart c " +
    	       "JOIN ci.product p " +
    	       "WHERE c.isCheckout = true " +
    	       "GROUP BY c.userId")
    List<Map<String, Object>> calculateTotalAmountPerUser();
}
