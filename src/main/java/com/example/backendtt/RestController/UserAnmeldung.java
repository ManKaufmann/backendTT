package com.example.backendtt.RestController;

import com.example.backendtt.componenten.login.Login;
import com.example.backendtt.security.dao.UserDataRepository;
import com.example.backendtt.security.models.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserAnmeldung {

    @Autowired
    private UserDataRepository userDataRepository;
    @Autowired
    private Login login;


    @PostMapping("/login")
    public ResponseEntity<?> loginwhithAuthenticationToken(
            @RequestHeader("authorization") String authorization
    ) {
        if (authorization != null && authorization.startsWith("Basic ")) {
            String[] decodedAuth = this.login.decodeBasicAuth(authorization);
            UserData data = new UserData(decodedAuth[0], decodedAuth[1], "ADMIN");

            if (this.userAuthoriesert(data)) {
                String jwtToken = login.signeJWTToken(data);
                return ResponseEntity.ok(jwtToken);
            }
        }
        return (ResponseEntity<?>) ResponseEntity.status(401);
    }
    @GetMapping("/login")
    public String logi(){
                return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MzE4NzU4MTYsImlhdCI6MTYzMTg3MjIxNiwiaXNzIjoibG9jYWxob3N0OjgwODUiLCJzdWIiOiJyb290IiwicG1zIjoiQURNSU4ifQ.6KjFU32ipp3JHEONKWaxWcphIv2K_xB31OtADxWOBAI";
    }


    @RequestMapping(value = {"/authenticate"}, method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody UserData userData,
            @RequestHeader MultiValueMap<String, String> headers
    ) {
        headers.forEach(
                (key, value) -> {
                    System.out.println(
                            String.format(
                                    "Header '%s' = %s", key, value.stream().collect(Collectors.joining("|")))
                    );
                }
        );

        if (this.userAuthoriesert(userData)) {


            String jwtToken = login.signeJWTToken(userData);
            return ResponseEntity.ok(jwtToken);
        }
        return (ResponseEntity<?>) ResponseEntity.status(401);
    }

    private boolean userAuthoriesert(UserData userData) {
        // TODO: 17.09.21
        return true;
    }

    @PostMapping("/token")
    public ResponseEntity validateToken(@RequestBody String tokenJWT, HttpServletResponse response) {
        // TODO: 17.09.21
        //Status 200 OK
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/register")
    public List<UserData> getusers() {
        List<UserData> allUser = userDataRepository.findAll();
        return allUser;
    }

    @PostMapping("/register")
    public ResponseEntity<UserData> saveUser(@RequestBody UserData newUser) {
        if (!(newUser.getUsername() == null) || !(newUser.getUserpassword() == null)) {
            UserData save = this.userDataRepository.save(newUser);
            return ResponseEntity.ok(save);
        }
        return (ResponseEntity<UserData>) ResponseEntity.noContent();
    }
}
