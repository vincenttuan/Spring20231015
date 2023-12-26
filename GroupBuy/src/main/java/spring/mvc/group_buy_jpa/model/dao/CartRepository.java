package spring.mvc.group_buy_jpa.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.mvc.group_buy_jpa.model.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	List<Cart> findByUserIdAndIsCheckout(Integer userId, Boolean isCheckout);
    List<Cart> findByUserId(Integer userId);
}