package spring.core.session03.mvc.model.service;

import java.util.List;

import spring.core.session03.mvc.model.bean.User;

public interface UserService {
	void addUser(User user); // 新增 user
	List<User> queryUsers(); // 查詢所有 user
}
