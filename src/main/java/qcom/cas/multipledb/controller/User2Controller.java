package qcom.cas.multipledb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qcom.cas.multipledb.entity2.User2;
import qcom.cas.multipledb.service.User2Service;

@RestController
@RequestMapping("user2")
public class User2Controller {
	
	@Autowired
	User2Service user2Service;
	
	@PostMapping(value="")
	public User2 saveUser2(@RequestBody User2 user2) {
		return user2Service.saveUser2(user2);
	}
	
	@GetMapping(value="")
	public List<User2> findAllUsers() {
		return user2Service.findAllUsers();
	}
	
	@PutMapping(value="/name")
	public String updateUsersName(@RequestBody User2 user) {
		user2Service.fetchAndUpdateFirstNameAndLastName(user.getId());
		return "Updated successfully";
	}

}
