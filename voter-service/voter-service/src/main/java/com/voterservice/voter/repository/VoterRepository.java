package com.voterservice.voter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voterservice.voter.entity.Voter;


@org.springframework.stereotype.Repository("voterRepository")
public interface VoterRepository extends JpaRepository<Voter, Integer>{
	Voter findByAadhaarNo(String aadhaarCardNo);
}
