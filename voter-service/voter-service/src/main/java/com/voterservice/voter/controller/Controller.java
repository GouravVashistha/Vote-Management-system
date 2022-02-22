package com.voterservice.voter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voterservice.voter.entity.Voter;
import com.voterservice.voter.exceptions.InvalidFieldException;
import com.voterservice.voter.service.VoterService;

@RestController
@RequestMapping("/voter")
public class Controller {
	
	@Autowired
	private VoterService voterService;
	
	@PostMapping("/")
	public Voter saveVoterDetails(@RequestBody Voter voter) {
		return voterService.saveVoterDetails(voter);
	}
	
	@PostMapping("/register-voter")
	public ResponseEntity<Voter> registerVoter(@RequestBody Voter voter) throws InvalidFieldException {
		Voter result = voterService.registerVoter(Voter voter);
		ResponseEntity<Voter> res = new ResponseEntity<>(result, HttpStatus.OK);
		return res;
	}
	
//	@GetMapping
//	public Voter displayCandidateProfile() {
//		
//	}
}

