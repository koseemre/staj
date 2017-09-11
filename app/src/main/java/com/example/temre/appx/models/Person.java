package com.example.temre.appx.models;


/**
 * Created by temre on 9.08.2017.
 */

public class Person extends Login{

    private boolean request;
    private boolean isGivenRequest;
    private int id;
    private String name;
    private String surname;
    private String start_date;
    private String end_date;
    private String excuse;
    private float total;
    private float remainingComp;
    private float remainingVac;




    public Person(){

        super();
        this.isGivenRequest = false;
        this.request = false;
        this.name = "name";
        this.surname = "surname";
        this.start_date = "start_date";
        this.end_date = "end_date";
        this.excuse = "excuse";
        this.remainingComp=0;
        this.remainingVac=0;

    }
    public Person(String username, String password, int role) {
        super(username, password, role);
        this.isGivenRequest = false;
        this.request = false;
        this.name = "name";
        this.surname = "surname";
        this.start_date = "start_date";
        this.end_date = "end_date";
        this.excuse = "excuse";
        this.remainingComp=0;
        this.remainingVac=0;
    }
    public Person(boolean request, boolean isGivenRequest, int id, String name, String surname, String start_date, String end_date, String excuse, float total, int remainingComp, int remainingVac) {
        this.request = request;
        this.isGivenRequest = isGivenRequest;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.start_date = start_date;
        this.end_date = end_date;
        this.excuse = excuse;
        this.total = total;
        this.remainingComp = remainingComp;
        this.remainingVac = remainingVac;
    }

    public Person(String name, String surname, String start_date, String end_date, String excuse) {

        super();
        this.name = name;
        this.surname = surname;
        this.start_date = start_date;
        this.end_date = end_date;
        this.excuse = excuse;
    }

    public Person(String username, String password, String name, String surname, String start_date, String end_date, String excuse) {
        super(username, password);
        this.name = name;
        this.surname = surname;
        this.start_date = start_date;
        this.end_date = end_date;
        this.excuse = excuse;
    }
    public Person(String username, String password,int role, String name, String surname, String start_date, String end_date, String excuse,float total) {
        super(username, password,role);
        this.name = name;
        this.surname = surname;
        this.start_date = start_date;
        this.end_date = end_date;
        this.excuse = excuse;
        this.total = total;
    }

    public float getRemainingComp() {
        return remainingComp;
    }

    public void setRemainingComp(float remainingComp) {
        this.remainingComp = remainingComp;
    }

    public float getRemainingVac() {
        return remainingVac;
    }

    public void setRemainingVac(float remainingVac) {
        this.remainingVac = remainingVac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRequest() {
        return request;
    }

    public void setRequest(boolean request) {
        this.request = request;
    }

    public boolean isGivenRequest() {
        return isGivenRequest;
    }

    public void setGivenRequest(boolean givenRequest) {
        isGivenRequest = givenRequest;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
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

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getExcuse() {
        return excuse;
    }

    public void setExcuse(String excuse) {
        this.excuse = excuse;


    }
}




