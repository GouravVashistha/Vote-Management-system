package com.candidate.controller;

import com.candidate.Vo.ResponseTempletVo;
import com.candidate.entity.Candidate;
import com.candidate.entity.CandidateLogin;
import com.candidate.exception.EmailAlreadyExistException;
import com.candidate.exception.EmailIdFormatException;
import com.candidate.exception.ShowException;
import com.candidate.exception.WrongCredentialsException;
import com.candidate.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("candidate")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @PostMapping("/CandidateRegistration")
    public Candidate registerCandidate(@RequestBody Candidate candidate) throws EmailAlreadyExistException, EmailIdFormatException {
        String tempEmailId=candidate.getEmailId();
        if (tempEmailId != null && !"".equals(tempEmailId)) {
            Candidate userObj = candidateService.fetchUserByEmailId(tempEmailId);
            if (userObj != null) {
                throw new EmailAlreadyExistException("User with " + tempEmailId + " is already Exist");
            }
        }

        if(tempEmailId.isEmpty() || tempEmailId.isBlank() || !tempEmailId.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            throw new EmailIdFormatException("Email Id is in Wrong Format");
        }

//        String tempPassword = candidate.getPassword();
//        if(tempPassword.length()<8){
//            throw new ShowException("Password length is Less tha 8 digit");
//        }
//        Date DOP= candidate.getDateOfBirth();

        return candidateService.registerCandidate(candidate);
    }


    @PostMapping("/candidateLogin")
    public String loginCandidate(@RequestBody CandidateLogin candidate) throws WrongCredentialsException, EmailAlreadyExistException {
        String tempEmailId = candidate.emailId.toString();
        String tempPass = candidate.password.toString();

        Candidate userObj = null;
        if (tempEmailId != null && tempPass != null) {
            userObj = candidateService.fetchCandidateByEmailIdAndPassword(tempEmailId, tempPass);
        }
        if (userObj == null) {
            throw new WrongCredentialsException("WrongCredentials");
        }
        return "login SuccessFull";
    }

    @GetMapping("/getByEmail/{emailId}")
    public  ResponseTempletVo getByCandidateId(@PathVariable String emailId){
    ResponseTempletVo candidate = candidateService.getUserByEmail(emailId);
        if (candidate != null) {
            return candidate;
        } else {
            throw new ShowException();
        }
    }

//    @GetMapping("/getById/{cId}")
//    public Candidate getByCandidateId(@PathVariable Integer cid){
//        Candidate candidate = candidateService.getCandidateById(cid);
//        if(candidate!=null) {
//            return candidate;
//        }
//        else {
//            throw new  ShowException();
//        }
//    }


    @PutMapping("/editCandidateInfo")
    public Candidate editInfo(@RequestBody Candidate candidate){

        return candidateService.editInfo(candidate);
    }

    @PutMapping("/changePassword")
    public Candidate changePassword(@RequestBody Candidate candidate){

        return  candidateService.changePassword(candidate);
    }

    @GetMapping("/increaseVote/{emailId}")
    public  ResponseTempletVo increaseByCandidateId(@PathVariable String emailId){
        ResponseTempletVo candidate = candidateService.increaseByCandidateId(emailId);
        if (candidate != null) {
            return candidate;
        } else {
            throw new ShowException();
        }
    }

    @GetMapping("/getalldata")
    public List<ResponseTempletVo> getAllCandidate(){
        return candidateService.getAllCandidate();
    }


    @GetMapping("/getByid/{cid}")
    public  Candidate getById(@PathVariable Integer cid){
        Candidate candidate = candidateService.getByCid(cid);
        if (candidate != null) {
            return candidate;
        } else {
            throw new ShowException();
        }
    }
}
