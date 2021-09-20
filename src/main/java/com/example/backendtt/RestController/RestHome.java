package com.example.backendtt.RestController;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(".*")
public class RestHome {
    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/hallo")
    public String hallo(){
        return "hallo back";
    }
}
