package qcom.cas.multipledb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qcom.cas.multipledb.entity1.User1;
import qcom.cas.multipledb.repository1.User1Repository;

@Service
public class User1Service {
	
	@Autowired
	User1Repository user1Repository;
	
	public User1 save(User1 user1) {
		return user1Repository.save(user1);
	}
	
	public List<User1> getAllUsers() {
		return (List<User1>)user1Repository.findAll();
	}
	
	@Transactional("transactionManager")
	public void fetchAndUpdateFirstNameAndLastName(int userId) {
		User1 user1 = user1Repository.findById(userId).orElse(null);
		if(null != user1) {
			user1Repository.updateFirstName(user1.getId(), user1.getFirstName().toUpperCase());
			user1Repository.updateLastName(user1.getId(), user1.getLastName().toUpperCase());
		}
	}

}
