package com.voterservice.voter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Voter")
public class Voter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="voter_id")
	private long voterId;
	
	@Column(name="voter_name")
	private String name;
	
	@Column(name="voter_age")
	private Integer Age;
	
	@Column(name="voter_aadhaar")
	private String aadhaarCardNo;
	
	@Column(name="voter_status")
	private Boolean status;
	
	@Column(name="voter_password")
	private String password;
	

}
