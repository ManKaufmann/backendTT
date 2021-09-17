package com.example.backendtt.RestController;

import com.example.backendtt.security.dao.UserDataRepository;
import com.example.backendtt.security.models.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class UserAnmeldung {

    @Autowired
    private UserDataRepository userDataRepository;

    @GetMapping("/login")
    public String login(){
        return "Hallo Anmeldedienst";
    }
    @GetMapping("/register")
    public List<UserData> getusers(){
        List<UserData> allUser = userDataRepository.findAll();
        return  allUser;
    }
    @PostMapping("/register")
    public ResponseEntity<UserData> saveUser(@RequestBody UserData newUser){
        if(!(newUser.getUsername() == null) || !(newUser.getUserpassword() == null)){
            UserData save = this.userDataRepository.save(newUser);
            return ResponseEntity.ok(save);
        }
        return (ResponseEntity<UserData>) ResponseEntity.noContent();
    }
}
