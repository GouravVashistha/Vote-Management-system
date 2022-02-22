package com.microservice.user.service;

import com.microservice.user.exception.EmailAlreadyExistException;
import com.microservice.user.helper.userDtoToUser;
import com.microservice.user.userDTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.user.entity.User;
import com.microservice.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private userDtoToUser UserDtoToUser;

	@Autowired
	private RestTemplate restTemplate;

	public User saveUser(User user) {
		user.setStatus(false);
		return userRepository.save(user);
	}

//	public ResponseTemplateVO getUserWithDepartment(Long userId) {
//
//		ResponseTemplateVO responseTemplateVO= new ResponseTemplateVO();
//		User user= userRepository.findByUserId(userId);
//
//		Department department=
//				restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/findDepartment/"+ user.getDepartmentId()
//						,Department.class);
//
//		responseTemplateVO.setUser(user);
//		responseTemplateVO.setDepartment(department);
//
//		return responseTemplateVO;
//	}


	public User updateVoter(UserDTO userDTO) throws Exception {
		User user = UserDtoToUser.convertUserDtoToUser(userDTO);
		User userReturn = userRepository.findByVoterId(user.getVoterId());
		if(userReturn.getVoterId()==0 && userReturn==null){
			throw new Exception();
		}
		else{
			userReturn.setName(user.getName());
			userReturn.setDOB(user.getDOB());
			return userRepository.save(userReturn);
		}

	}

	public User updatePassword(User user) {
		return userRepository.save(user);
	}


	public User registerVoter(User user) {
		user.setStatus(false);
		return userRepository.save(user);
	}

	public User fetchVoterByAadhaarAndName(String aadhaar, String voterPassword) throws EmailAlreadyExistException {
		User user=userRepository.findByAadhaarCardNo(aadhaar);
		if(user.getPassword().equals(voterPassword)){
			return user;
		}
		else{
			throw  new EmailAlreadyExistException();
		}
	}
}
