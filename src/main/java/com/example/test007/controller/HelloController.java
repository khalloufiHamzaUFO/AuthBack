package com.example.test007.controller;

import com.example.test007.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    @GetMapping("/")
    public String sayHello(){
        return "Hello world!";
    }

    @GetMapping("/secured")
    public String secured (@AuthenticationPrincipal UserPrincipal userPrincipal){
        return "if u see this u r loggeed in "+ userPrincipal.getEmail()+" UserId : " + userPrincipal.getUserId() ;
    }

    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return "if u see this u r ADMINN loggeed in "+ userPrincipal.getEmail()+" UserId : " + userPrincipal.getUserId() ;
    }
}
