package com.smartbusstopbackend.app.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.smartbusstopbackend.app.models.User;
import com.smartbusstopbackend.app.services.UserService;

@Controller
@RequestMapping("/users")
public class UserRestController {
	
	private UserService userService;
	
	@Autowired
	public UserRestController(UserService userService){
		this.userService = userService;
	}
	
	@ResponseBody
	@GetMapping(name = "/")
	public List<User> getAllUsers(){
		return userService.findAll();
	}
	
}
