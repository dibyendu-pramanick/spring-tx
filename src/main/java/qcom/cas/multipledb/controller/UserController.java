package qcom.cas.multipledb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qcom.cas.multipledb.entity1.User1;
import qcom.cas.multipledb.service.CommonService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private CommonService commonService;
	
	@PutMapping(value="/name")
	public String updateUsersName(@RequestBody User1 user) {
		commonService.updateBothUser(user);
		return "Updated successfully";
	}

}
