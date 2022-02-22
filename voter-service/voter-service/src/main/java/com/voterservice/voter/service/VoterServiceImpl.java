package com.voterservice.voter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.voterservice.voter.entity.Voter;
import com.voterservice.voter.exceptions.IncorrectLoginCredentialsException;
import com.voterservice.voter.exceptions.InvalidFieldException;
import com.voterservice.voter.repository.VoterRepository;

@Service
public class VoterServiceImpl implements VoterService {
	
	@Autowired
	private VoterRepository voterRepo;
	
	@Override
	public Voter voterLogin(String aadhaarCardNo, String name, String password) throws IncorrectLoginCredentialsException{
		Voter v = voterRepo.findByAadhaarNo(aadhaarCardNo);
		try {
			if(v.getAadhaarCardNo().equals(aadhaarCardNo) && v.getName().equals(name) && v.getPassword().equals(password)) {
				return v;
			}
			else {
				throw new IncorrectLoginCredentialsException("Login Credentials are incorrect");
			}
		}
		catch(IncorrectLoginCredentialsException e) {
			return null;
		}
	}
	
	@Override
	public Voter registerVoter(Voter voter) {
		try {
			long vid = voter.getVoterId();
			String vName = voter.getName();
			Integer vAge = voter.getAge();
			String vAadhaar = voter.getAadhaarCardNo();
			String vPass = voter.getPassword();
			boolean isValid = true;
			if(vName.isEmpty()) {
				voter.setName("name should not be empty");
				isValid = false;
				throw new InvalidFieldException("name should not be empty");
			}
			if(vAge < 18 ) {
				isValid = false;
				throw new InvalidFieldException("must be above 18");
			}
			if(vAadhaar.length() < 12 ) {
				voter.setAadhaarCardNo();
				isValid = false;
				throw new InvalidFieldException("Incorrect Aadhaar number");
			}
			if(vPass.length() < 8 ) {
				voter.setPassword("must have 8 characters");
				isValid = false;
				throw new InvalidFieldException("must have 8 characters");
			}
			
			if(isValid) {
				voter.setName(vName);
				voter.setAge(vAge);
				voter.setPassword(vPass);
				voter.setAadhaarCardNo(vAadhaar);
				voterRepo.saveAndFlush(voter);
			}
			
		}
		catch(InvalidFieldException e) {
			return null;
		}
	}
}
