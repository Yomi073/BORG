package com.borg.model;

import java.util.Date;

public class ViewUsersTasks {
    int id;
    String firstName;
    String address;
    String date;

    public ViewUsersTasks() {
    }

    public ViewUsersTasks(int task_id, String client_name, String client_address, String date) {
        this.id = task_id;
        this.firstName = client_name;
        this.address = client_address;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstName;
    }

    public void setFirstname(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
