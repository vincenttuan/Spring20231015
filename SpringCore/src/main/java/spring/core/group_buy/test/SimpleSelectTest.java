package spring.core.group_buy.test;

import java.util.List;

import spring.core.group_buy.dao.GroupBuyDao;
import spring.core.group_buy.dao.GroupBuyDaoMySQL;
import spring.core.group_buy.entity.User;

public class SimpleSelectTest {

	public static void main(String[] args) {
		GroupBuyDao dao = new GroupBuyDaoMySQL();
		List<User> users = dao.findAllUsers();
		System.out.println(users.size());
		System.out.println(users);
	}

}
