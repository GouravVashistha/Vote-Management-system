package com.candidate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name="Candidate_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={ "emailId"})

)
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
