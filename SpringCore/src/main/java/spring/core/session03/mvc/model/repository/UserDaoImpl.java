package spring.core.session03.mvc.model.repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import spring.core.session03.mvc.model.bean.User;

public class UserDaoImpl implements UserDao {
	
	// 定義一個 In-memory 集合來放 user 物件資料 
	private static List<User> users = new CopyOnWriteArrayList<>();
	
	@Override
	public void create(User user) {
		System.out.println("呼叫 UserDao create()");
		users.add(user);
	}

	@Override
	public List<User> readUsers() {
		return users;
	}
	
}
