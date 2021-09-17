package com.example.backendtt.RestController;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestHome {
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
