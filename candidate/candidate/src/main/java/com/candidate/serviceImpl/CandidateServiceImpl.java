package com.candidate.serviceImpl;

import com.candidate.Dao.CandidateReposistory;
import com.candidate.Vo.ResponseTempletVo;
import com.candidate.Vo.Vote;
import com.candidate.entity.Candidate;
import com.candidate.exception.EmailAlreadyExistException;
import com.candidate.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    CandidateReposistory candidateReposistory;
//-----------------------------------------------------
    @Autowired
    RestTemplate restTemplate;

//----------------------------------------------------
    @Override
    public Candidate fetchUserByEmailId(String email) {
        return candidateReposistory.findByEmailId(email);

    }

    @Override
    public Candidate registerCandidate(Candidate candidate) {
        RestTemplate restTemplate =new RestTemplate();
        Vote voteOne = new Vote();
        voteOne.setVotes(0l);
        ResponseEntity<Vote>  vote = restTemplate.postForEntity( "http://localhost:9002/vote/vote", voteOne, Vote.class );

        Vote voteReturn = vote.getBody();
        candidate.setVoteId(voteReturn.getVotesId());
        return candidateReposistory.save(candidate);
    }


    @Override
    public Candidate fetchCandidateByEmailIdAndPassword(String tempEmailId, String tempPass) throws EmailAlreadyExistException {
         Candidate candidate=candidateReposistory.findByEmailId(tempEmailId);
        if(candidate.getPassword().equals(tempPass)){
            return candidate;
        }
        else{
            throw  new EmailAlreadyExistException();
        }
    }

    @Override
    public Candidate editInfo(Candidate candidate) {
        Candidate validCandidate =candidateReposistory.findByCid(candidate.getCid()) ;
        if(validCandidate.getCName()==null && candidate.getCid()==0){
           return  null;
        }
        else {
            return candidateReposistory.save(candidate);
        }
    }

    @Override
    public Candidate changePassword(Candidate candidate) {
        Candidate validCandidate =candidateReposistory.findByCid(candidate.getCid()) ;

        return null;
    }

    @Override
    public ResponseTempletVo increaseByCandidateId(String emailId) {
        Candidate user = candidateReposistory.findByEmailId(emailId);
        if(user!=null) {
            ResponseEntity<Vote > vote= restTemplate.getForEntity("http://VOTE-SERVICE/vote/incVote/"+user.getVoteId(), Vote.class);
            ResponseTempletVo vo = new ResponseTempletVo();
            vo.setCandidate(user);
            vo.setVote(vote.getBody());
            return vo;
        }
        else {
            return  null;
        }
    }

    @Override
    public List<ResponseTempletVo> getAllCandidate() {
        List<Candidate> list = candidateReposistory.findAll();
        List<ResponseTempletVo> li = new ArrayList<>();
        for(Candidate c:list){
            ResponseTempletVo responseTempletVo = new ResponseTempletVo();
            ResponseEntity<Vote > vote= restTemplate.getForEntity("http://VOTE-SERVICE/vote/vote/"+c.getVoteId(), Vote.class);
            responseTempletVo.setCandidate(c);
            responseTempletVo.setVote(vote.getBody());
            li.add(responseTempletVo);

        }
        return  li;
    }

    @Override
    public Candidate getByCid(Integer cid) {
        return candidateReposistory.getByCid(cid);
    }
//    @Override
//    public Candidate getByCid(Integer cid) {
////        return candidateReposistory.getByCid(cid);
//
//        Candidate user = candidateReposistory.getByCid(cid);
//        if(user!=null) {
//            ResponseEntity<Vote > vote= restTemplate.getForEntity("http://VOTE-SERVICE/vote/vote/"+user.getVoteId(), Vote.class);
//            ResponseTempletVo vo = new ResponseTempletVo();
//            vo.setCandidate(user);
//            vo.setVote(vote.getBody());
//            return vo;
//        }
//        else {
//            return  null;
//        }
//    }


    @Override
    public ResponseTempletVo getUserByEmail(String emailId) {
        Candidate user = candidateReposistory.findByEmailId(emailId);
        if(user!=null) {
            ResponseEntity<Vote > vote= restTemplate.getForEntity("http://VOTE-SERVICE/vote/vote/"+user.getVoteId(), Vote.class);
            ResponseTempletVo vo = new ResponseTempletVo();
            vo.setCandidate(user);
            vo.setVote(vote.getBody());
            return vo;
        }
        else {
            return  null;
        }


    }

    @Override
    public ResponseTempletVo getUserByID(Integer cid) {
        Candidate user = candidateReposistory.findByCid(cid);

        if(user!=null) {
            ResponseEntity<Vote > vote= restTemplate.getForEntity("http://VOTE-SERVICE/vote/vote/"+user.getCid(), Vote.class);
            ResponseTempletVo vo = new ResponseTempletVo();
            vo.setCandidate(user);
            vo.setVote(vote.getBody());
            return vo;
        }
        else {
            return  null;
        }

    }



}


