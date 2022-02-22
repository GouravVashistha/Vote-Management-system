package com.admin.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTempletVo1 {
    private  Candidate candidate;
    private Vote vote;
}
