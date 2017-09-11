package com.example.temre.appx.models;

import java.sql.Date;

/**
 * Created by temre on 25.08.2017.
 */
public class personInfo {



    private String name;
    private String surname;
    private String username;
    private String email;
    private int locationId;
    private Date birthDate;
    private int id;
    private String tel;

    public personInfo(){

    }

    public personInfo(String name, String surname, String username, String email, int locationId, Date birthDate,
                      int id,String tel) {
        super();
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.locationId = locationId;
        this.birthDate = birthDate;
        this.id = id;
        this.tel = tel;

    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


}
