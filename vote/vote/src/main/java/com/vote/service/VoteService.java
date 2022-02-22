package com.vote.service;

import com.vote.entity.Vote;

public interface VoteService {
    Vote registerVote(Vote vote);


    Vote getVoteById(Integer votesId);

    Vote increaseVote(Integer votesId);
}
