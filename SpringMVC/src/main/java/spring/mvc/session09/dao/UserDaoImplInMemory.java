package spring.mvc.session09.dao;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.session09.entity.User;

@Repository("userDaoImplInMemory")
public class UserDaoImplInMemory implements UserDao {
	
	private static List<User> users = new CopyOnWriteArrayList<>();
	private static AtomicInteger atomicId = new AtomicInteger(0);
	
	@Autowired
	private DataDao dataDao;
	
	@Override
	public int addUser(User user) {
		user.setId(atomicId.incrementAndGet());
		users.add(user);
		return 1;
	}

	@Override
	public int updateUserById(Integer id, User user) {
		Optional<User> curUserOpt = getUserById(id);
		if(curUserOpt.isPresent()) {
			User curUser = curUserOpt.get();
			curUser.setName(user.getName());
			curUser.setAge(user.getAge());
			curUser.setBirth(user.getBirth());
			
			curUser.setEducationId(user.getEducationId());
			curUser.setEducation(dataDao.getEducationDataById(user.getEducationId()).get());
			
			curUser.setSexId(user.getSexId());
			curUser.setSex(dataDao.getSexDataById(user.getSexId()).get());
			
			curUser.setInterestIds(user.getInterestIds());
			curUser.setInterests(dataDao.findInterestDatasByIds(user.getInterestIds()));
			
			curUser.setResume(user.getResume());
			
			return 1;
		}
		return 0;
	}

	@Override
	public int deleteUserById(Integer id) {
		Optional<User> curUserOpt = getUserById(id);
		if(curUserOpt.isPresent()) {
			User curUser = curUserOpt.get();
			users.remove(curUser);
			return 1;
		}
		return 0;
	}

	@Override
	public Optional<User> getUserById(Integer id) {
		Optional<User> userOpt = users.stream().filter(user -> user.getId().equals(id)).findFirst();
		return userOpt;
	}

	@Override
	public List<User> findAllUsers() {
		return users;
	}
	
}
