package com.vote.Dao;

import com.vote.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote,Integer> {

    Vote findByVotesId(Integer votesId);
}
