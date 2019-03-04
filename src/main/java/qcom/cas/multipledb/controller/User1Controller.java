package qcom.cas.multipledb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qcom.cas.multipledb.entity1.User1;
import qcom.cas.multipledb.service.User1Service;

@RestController
@RequestMapping("user1")
public class User1Controller {
	
	@Autowired
	User1Service user1Service;
	
	@PostMapping(value="")
	public User1 saveUser1(@RequestBody User1 user1) {
		return user1Service.save(user1);
	}
	
	@GetMapping(value="")
	public List<User1> findAllUsers() {
		return user1Service.getAllUsers();
	}
	
	@PutMapping(value="/name")
	public String updateUsersName(@RequestBody User1 user) {
		user1Service.fetchAndUpdateFirstNameAndLastName(user.getId());
		return "Updated successfully";
	}

}
