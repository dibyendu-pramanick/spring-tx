package qcom.cas.multipledb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qcom.cas.multipledb.entity1.User1;
import qcom.cas.multipledb.repository1.User1Repository;
import qcom.cas.multipledb.repository2.User2Repository;

@Service
public class CommonService {
	
	@Autowired
	private User1Repository user1Repository;
	
	@Autowired
	private User2Repository user2Repository;
	
	@Transactional("transactionManager")
	public void updateBothUser(User1 user) {
		if(user1Repository.findById(user.getId()) != null &&
				user2Repository.findById(user.getId()) != null) {
			user1Repository.updateFirstName(user.getId(), "Test1".toUpperCase());
			user2Repository.updateLastName(user.getId(), "Test2".toUpperCase());
		}
	}

}
