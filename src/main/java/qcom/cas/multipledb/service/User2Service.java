package qcom.cas.multipledb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qcom.cas.multipledb.entity2.User2;
import qcom.cas.multipledb.repository2.User2Repository;

@Service
public class User2Service {
	
	@Autowired
	User2Repository user2Repository;
	
	public User2 saveUser2(User2 user2) {
		return user2Repository.save(user2);
	}
	
	public List<User2> findAllUsers() {
		return (List<User2>)user2Repository.findAll();
	}
	
	@Transactional("transactionManager")
	public void fetchAndUpdateFirstNameAndLastName(int userId) {
		User2 user2 = user2Repository.findById(userId).orElse(null);
		if(null != user2) {
			user2Repository.updateFirstName(user2.getId(), user2.getFirstName().toUpperCase());
			user2Repository.updateLastName(user2.getId(), user2.getLastName().toUpperCase());
		}
	}

}
