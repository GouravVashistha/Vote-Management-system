package com.admin.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {
    private Integer cid;
    private String cName;
    private String emailId;
    private String password;
    private String contactNumber;
    private Date dateOfBirth;
    private String City;
    private String Gender;
    private String candidate_photo;
    private String candidate_logo;
    private String manifesto;
    private Integer voteId;
}
