package spring.mvc.group_buy_jpa.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.mvc.group_buy_jpa.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}

