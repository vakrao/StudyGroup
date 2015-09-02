package com.raov3.studygroup;

/**
 * Created by raov3 on 8/11/2015.
 */
public class User {

    String username;
    String password;
    String email;
    public User(String u, String p, String e) {
        username = u;
        password = p;
        email = e;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getEmail(){
        return email;
    }
}
