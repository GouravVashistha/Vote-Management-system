package com.microservice.user.controller;

import com.microservice.user.entity.UserLogin;
import com.microservice.user.exception.EmailAlreadyExistException;
import com.microservice.user.userDTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.user.entity.User;
import com.microservice.user.service.UserService;

@RestController
@RequestMapping("/voter")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/VoterRegistration")
	public User registerVoter(@RequestBody User user){
		return  userService.registerVoter(user);
	}

	@PostMapping("/voterLogin")
	public String loginVoter(@RequestBody UserLogin user) throws com.voterservice.voter.exceptions.IncorrectCredentialsException, EmailAlreadyExistException {
		String aadhar = user.aadhaarCardNo.toString();
		String voterPassword= user.password.toString();

		User userObj = null;
		if ( aadhar != null && voterPassword != null) {
			userObj = userService.fetchVoterByAadhaarAndName(aadhar, voterPassword);
		}
		if (userObj == null) {
			throw new com.voterservice.voter.exceptions.IncorrectCredentialsException("Incorrect Credentials");
		}
		return "login SuccessFull";
	}


		@PostMapping("/save")
	public User saveUser(@RequestBody User user) {
		
		return userService.saveUser(user);
	}
	@PostMapping("/updateVoter")
	public User updateVoter(@RequestBody UserDTO userDto) throws Exception {
		return  userService.updateVoter(userDto);
	}

	@PostMapping("/updatePassword")
	public User updatePassword(@RequestBody User user){
		return  userService.updatePassword(user);
	}

}
