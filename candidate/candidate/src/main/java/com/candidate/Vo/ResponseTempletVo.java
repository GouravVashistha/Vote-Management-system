package com.candidate.Vo;

import com.candidate.entity.Candidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ResponseTempletVo {

    private Candidate candidate;
    private  Vote vote;

}
