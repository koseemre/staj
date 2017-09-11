package com.example.temre.appx.models;

/**
 * Created by temre on 17.08.2017.
 */

public class Login {

    private String username;
    private String password;
    private int role;


    public Login() {
        this.username = "noUsername";
        this.password = "noPassword";
        this.role =0;
    }
    public Login(String username, String password) {

        this.username = username;
        this.password = password;
        this.role =0;

    }


    public Login(String username, String password, int role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
