package com.vote.controller;

import com.vote.entity.Vote;
import com.vote.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    VoteService voteService;


    @PostMapping("/vote")
    public Vote registerVote(@RequestBody Vote vote){
        return voteService.registerVote(vote);

    }
    @GetMapping("/vote/{votesId}")
    public Vote getVoteById(@PathVariable Integer votesId){
        return voteService.getVoteById(votesId);
    }


    @GetMapping("/incVote/{votesId}")
    public Vote increaseVote(@PathVariable Integer votesId){
        return voteService.increaseVote(votesId);
    }

}
