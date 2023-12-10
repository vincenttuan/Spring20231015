package spring.mvc.session09.dao;

import java.util.List;
import java.util.Optional;

import spring.mvc.session09.entity.User;

public interface UserDao {
	
	int addUser(User user);
	int updateUserById(Integer id, User user);
	int deleteUserById(Integer id);
	Optional<User> getUserById(Integer id);
	List<User> findAllUsers();
	
}
