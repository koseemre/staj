package com.example.temre.appx.models;

import java.util.List;

/**
 * Created by temre on 21.08.2017.
 */

public class Manager extends Person {

    List<String> myTeamMembers;

    public Manager(List<String> myTeamMembers) {
        this.myTeamMembers = myTeamMembers;
    }

    public Manager(String name, String surname, String start_date, String end_date, String excuse, List<String> myTeamMembers) {
        super(name, surname, start_date, end_date, excuse);
        this.myTeamMembers = myTeamMembers;
    }

    public Manager(String username, String password, String name, String surname, String start_date, String end_date, String excuse, List<String> myTeamMembers) {
        super(username, password, name, surname, start_date, end_date, excuse);
        this.myTeamMembers = myTeamMembers;
    }

    public List<String> getMyTeamMembers() {
        return myTeamMembers;
    }

    public void setMyTeamMembers(List<String> myTeamMembers) {
        this.myTeamMembers = myTeamMembers;
    }
}
