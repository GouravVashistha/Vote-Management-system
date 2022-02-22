package com.candidate.Dao;

import com.candidate.Vo.ResponseTempletVo;
import com.candidate.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateReposistory extends JpaRepository<Candidate,Integer> {

     Candidate findByEmailId(String emailId);


    Candidate findByEmailIdAndPassword(String emailId, String password);

    Candidate findByCid(Integer cid);

    Candidate getByCid(Integer cid);
}
