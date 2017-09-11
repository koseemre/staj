package com.example.temre.appx.models;

/**
 * Created by temre on 6.09.2017.
 */

public class Reason {
    int id;
    String reason;
    int day;
    int priority;

    public Reason(int id, String reason) {
        super();
        this.id = id;
        this.reason = reason;
        this.day = 0;
        this.priority = 0;
    }

    public Reason(int id, String reason, int day) {
        super();
        this.id = id;
        this.reason = reason;
        this.day = day;
        this.priority = 0;
    }

    public Reason(int id, String reason, int day, int priority) {
        super();
        this.id = id;
        this.reason = reason;
        this.day = day;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}
