package com.candidate.service;

import com.candidate.Vo.ResponseTempletVo;
import com.candidate.entity.Candidate;
import com.candidate.exception.EmailAlreadyExistException;

import java.util.List;

public interface CandidateService {


    public ResponseTempletVo getUserByEmail(String email);

    Candidate fetchUserByEmailId(String tempEmailId);

    Candidate registerCandidate(Candidate candidate);


    Candidate fetchCandidateByEmailIdAndPassword(String tempEmailId, String tempPass) throws EmailAlreadyExistException;


    Candidate editInfo(Candidate candidate);

    Candidate changePassword(Candidate candidate);

    ResponseTempletVo increaseByCandidateId(String emailId);

    List<ResponseTempletVo> getAllCandidate();

    Candidate getByCid(Integer cid);

    ResponseTempletVo getUserByID(Integer cid);
}
