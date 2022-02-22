package com.admin.service;

import com.admin.Dao.AdminReposistory;
import com.admin.Vo.Candidate;
import com.admin.Vo.ResponseTempletVo1;
import com.admin.entity.Admin;
import com.admin.exception.EmailAlreadyExistException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {
    @Autowired
    AdminReposistory adminReposistory;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    public Admin addAdmin(Admin admin) {
        return adminReposistory.save(admin);
    }

    public String login(String email, String password) {
        return  null;

    }

    public List<Candidate> getAllCandidate(){
        List<Candidate> c = restTemplateBuilder.build().getForObject("http://localhost:9001/candidate/getalldata", List.class);
        return  c;
    }


    public Candidate getCandidateData(Integer cid) {
        RestTemplate r = new RestTemplate();
        Candidate candidate= r.getForObject("http://localhost:9001/candidate/getByid/"+cid, Candidate.class);
        return  candidate;

    }


    public Candidate editCandidateData(Candidate candidate) {
        RestTemplate r = new RestTemplate();
         r.put("http://localhost:9001/candidate/editCandidateInfo",candidate);
        Candidate candidateReturn= r.getForObject("http://localhost:9001/candidate/getByid/"+candidate.getCid(), Candidate.class);
        return  candidateReturn;
    }

    public Admin fetchUserByEmailId(String tempEmailId) {
        return adminReposistory.findByEmailId(tempEmailId);
    }

    public Admin fetchAdminByEmailIdAndPassword(String tempEmailId, String tempPass) throws EmailAlreadyExistException {
        Admin admin = adminReposistory.findByEmailId(tempEmailId);
        if (admin.getPassword().equals(tempPass)) {
            return admin;
        } else {
            throw new EmailAlreadyExistException();
        }
    }


    public ResponseTempletVo1 winnerCandidate() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity("http://localhost:9001/candidate/getalldata",Object[].class);
        Object[] objects = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        List<ResponseTempletVo1> product = Arrays.stream(objects)
                .map(object -> mapper.convertValue(object, ResponseTempletVo1.class))
                .collect(Collectors.toList());
        ResponseTempletVo1 rVo1 = product.stream().max(Comparator.comparing(a->a.getVote().getVotes())).get();
        return  rVo1;


    }

}
