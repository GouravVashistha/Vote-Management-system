package com.voterservice.voter.service;



import com.voterservice.voter.entity.Voter;

public interface VoterService {
		
	public Voter voterLogin(String aadhaarCardNo, String name);
	
	public Voter registerVoter(Voter voter);
	
	public Voter saveVoterDetails(Voter voter);
	
	// public Voter displayCandidateProfile();
	
	//
	
}
