package com.admin.Vo;

import com.admin.entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ResponseTempletVo {
    private Candidate candidate;
    private Admin admin;



}
