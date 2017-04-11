package com.example.hamid_pc.quizica;

/**
 * Created by Hamid-PC on 4/8/2017.
 */

public class User {
    private String uuid;
    private String UserName;
    private String Role;

    public User(String uuid, String userName, String role) {
        this.uuid = uuid;
        UserName = userName;
        Role = role;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}
