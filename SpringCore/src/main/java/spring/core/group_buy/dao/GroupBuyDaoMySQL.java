package spring.core.group_buy.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import spring.core.group_buy.entity.Cart;
import spring.core.group_buy.entity.Product;
import spring.core.group_buy.entity.User;

// 利用 JdbcTemplate 來實現對資料表的 CRUD
public class GroupBuyDaoMySQL implements GroupBuyDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public GroupBuyDaoMySQL() {
		// 手動方式實現 jdbcTemplate 物件初始
		String mySQLDriverName = "com.mysql.cj.jdbc.Driver"; // MySQL 驅動
		String dbURL = "jdbc:mysql://localhost:3306/group_buy?serverTimezone=Asia/Taipei";
		String username = "root";
		String password = "12345678";
		
		// 創建 DriverManagerDataSource
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(mySQLDriverName); // MySQL 驅動
		dataSource.setUrl(dbURL);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		
		// 設定 JdbcTemplate 的數據來源
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<User> findAllUsers() {
		String sql = "select userId, username, password, level from User";
		List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
		return users;
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean updateUserPassword(Integer userId, String newPassword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<User> findUserById(Integer userId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Product> findAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Product> findProductById(Integer productId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean updateProductLaunch(Integer productId, Boolean isLaunch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cart> findAllCarts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cart> findCartsBuyUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cart> findCartsByUserIdAndCheckoutStatus(Integer userId, Boolean isCheckout) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Cart> findNoneCheckoutCartByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void addCart(Cart cart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean checkoutCartsByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean checkoutCartBuyId(Integer cartId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean removeCartItemById(Integer cartItemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateCartItemQuantity(Integer cartItemId, Integer quantity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
