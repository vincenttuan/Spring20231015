package spring.mvc.group_buy_jpa.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.mvc.group_buy_jpa.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
