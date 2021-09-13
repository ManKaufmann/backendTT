package com.example.backendtt.security.models;

import javax.persistence.*;

@Table(name = "user_data")
@Entity
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "userpassword")
    private String userpassword;

    @Column(name = "berechtigung")
    private String berechtigung;

    public String getBerechtigung() {
        return berechtigung;
    }

    public void setBerechtigung(String berechtigung) {
        this.berechtigung = berechtigung;
    }

    public UserData() {
    }

    public UserData(String username, String userpassword, String berechtigung) {
        this.username = username;
        this.userpassword = userpassword;
        this.berechtigung = berechtigung;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "username = " + username + ")";
    }
}