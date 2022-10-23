package com.borg.model.database;

public class ViewUserTasks {

    private Integer id;
    private String firstName;
    private String address;
    private String date;

    public ViewUserTasks() {
    }

    public ViewUserTasks(Integer id, String firstName, String address, String date) {
        this.id = id;
        this.firstName = firstName;
        this.address = address;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
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
