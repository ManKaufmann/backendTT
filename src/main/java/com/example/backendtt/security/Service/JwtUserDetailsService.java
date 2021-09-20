package com.example.backendtt.security.Service;

import com.example.backendtt.security.dao.UserDataRepository;
import com.example.backendtt.security.models.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    UserDataRepository userDataRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserData> userLogin = userDataRepository.findByUsernameEquals(username);
        if(userLogin.isPresent()){
            UserData userData = userLogin.get();
            return new User(userData.getUsername(),userData.getUserpassword(),new ArrayList<>());
        }
        throw new UsernameNotFoundException("User nicht gefunden");
    }
}
