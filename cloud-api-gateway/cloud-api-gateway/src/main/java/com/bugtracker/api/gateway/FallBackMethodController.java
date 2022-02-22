package com.bugtracker.api.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/adminServiceFallBack")
    public String adminServiceFallBackMethod() {
        return "admin Service is taking longer than Expected. " +
                " Please try again later.";
    }

    @GetMapping("/candidateServiceFallBack")
    public String candidateServiceFallBackMethod() {
        return "candidate Service is taking longer than Expected. " +
                " Please try again later.";
    }

    @GetMapping("/voteServiceFallBack")
    public String voteServiceFallBackMethod() {
        return "vote Service is taking longer than Expected. " +
                " Please try again later.";
    }
    @GetMapping("/voterServiceFallBack")
    public String voterServiceFallBackMethod() {
        return "voter Service is taking longer than Expected. " +
                " Please try again later.";
    }

}

