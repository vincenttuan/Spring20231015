package spring.core.session03.mvc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import spring.core.session03.mvc.model.bean.User;
import spring.core.session03.mvc.model.repository.UserDao;

public class UserServiceImpl implements UserService {
	
	@Autowired
	// 自動綁定, 不需要 new, 因為會自動取得已經建立好的 UserDao 物件
	private UserDao userDao;
	
	@Override
	public void addUser(User user) {
		System.out.println("呼叫 UserService addUser()");
		userDao.create(user);
	}

	@Override
	public List<User> queryUsers() {
		return userDao.readUsers();
	}

}
