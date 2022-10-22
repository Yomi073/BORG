package com.borg.model.database;

public class ViewUserTasks {
    Integer id;
    String firstName;
    String address;
    String date;

    public ViewUserTasks() {
    }

    public ViewUserTasks(Integer task_id, String client_name, String client_address, String date) {
        this.id = task_id;
        this.firstName = client_name;
        this.address = client_address;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
