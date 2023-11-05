package spring.core.session03.mvc.model.repository;

import java.util.List;

import spring.core.session03.mvc.model.bean.User;

public interface UserDao {
	void create(User user); // 建立使用者
	List<User> readUsers(); // 取得所有使用者
}
