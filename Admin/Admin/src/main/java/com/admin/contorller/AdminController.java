package com.admin.contorller;

import com.admin.Vo.Candidate;
import com.admin.Vo.ResponseTempletVo1;
import com.admin.entity.Admin;
import com.admin.entity.AdminLogin;
import com.admin.exception.EmailAlreadyExistException;
import com.admin.exception.EmailIdFormatException;
import com.admin.exception.WrongCredentialsException;
import com.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/addAdmin")
    public Admin addAdmin(@RequestBody Admin admin) throws EmailIdFormatException, EmailAlreadyExistException {
        String tempEmailId=admin.getEmailId();
        if (tempEmailId != null && !"".equals(tempEmailId)) {
            Admin userObj = adminService.fetchUserByEmailId(tempEmailId);
            if (userObj != null) {
                throw new EmailAlreadyExistException("User with " + tempEmailId + " is already Exist");
            }
        }

        if(tempEmailId.isEmpty() || tempEmailId.isBlank() || !tempEmailId.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            throw new EmailIdFormatException("Email Id is in Wrong Format");
        }

        return adminService.addAdmin(admin);

    }

    @PostMapping("/adminLogin")
    public String loginAdmin(@RequestBody AdminLogin admin) throws WrongCredentialsException, EmailAlreadyExistException {
        String tempEmailId = admin.emailId.toString();
        String tempPass = admin.password.toString();

        Admin userObj = null;
        if (tempEmailId != null && tempPass != null) {
            userObj = adminService.fetchAdminByEmailIdAndPassword(tempEmailId, tempPass);
        }
        if (userObj== null) {
            throw new WrongCredentialsException("WrongCredentials");
        }
        return "login Successful";
    }

    @GetMapping("/CandidateData")
    public List<Candidate> getAllCandidate(){
        return  adminService.getAllCandidate();
    }

    @PutMapping("/editCandidateData")
     public  Candidate ediCandidateData(@RequestBody Candidate candidate){
        return adminService.editCandidateData(candidate);
    }

    @GetMapping("/getCandidateById/{cid}")
    public Candidate ediCandidateData(@PathVariable Integer cid){
        return  adminService.getCandidateData(cid);
    }


    @GetMapping("/winner")
    public ResponseTempletVo1 winnerCandidate(){
        return adminService.winnerCandidate();
    }


}
