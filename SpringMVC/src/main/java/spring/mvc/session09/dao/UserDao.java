package spring.mvc.session09.dao;

import java.util.Optional;

import spring.mvc.session09.entity.User;

public interface UserDao {
	
	int addUser(User user);
	int updateUser(User user);
	int deleteUser(User user);
	Optional<User> getUserById
	
}
