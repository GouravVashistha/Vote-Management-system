package com.vote.serviceImpl;

import com.vote.Dao.VoteRepository;
import com.vote.entity.Vote;
import com.vote.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    VoteRepository voteRepository;

    @Override
    public Vote registerVote(Vote vote) {
        return voteRepository.save(vote);
    }

    @Override
    public Vote getVoteById(Integer votesId) {
        return voteRepository.findByVotesId(votesId);
    }

    @Override
    public Vote increaseVote(Integer votesId) {
        Vote vote = voteRepository.findByVotesId(votesId);
        vote.setVotes(vote.getVotes()+1);
        return voteRepository.save(vote) ;
    }


}
