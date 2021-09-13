package com.example.backendtt;

import com.example.backendtt.security.dao.UserDataRepository;
import com.example.backendtt.security.models.UserData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.GenericSignatureFormatError;

@SpringBootApplication
public class BackendTtApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendTtApplication.class, args);
    }

    private  void insertStandartUser(UserDataRepository userDataRepository){
        UserData save = userDataRepository.save(new UserData("test", "test", "User"));

    }

    @Bean
    public CommandLineRunner run (UserDataRepository userDataRepository){


        return args -> {
            System.out.println("Start insert");
            this.insertStandartUser(userDataRepository);
            System.out.println(userDataRepository.findAll().toString());

        };
    }

}
