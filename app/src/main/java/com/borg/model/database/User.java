package com.borg.model.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "user", foreignKeys = @ForeignKey(entity = Role.class, parentColumns = "id",childColumns = "role_FK", onDelete = 5))
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", index = true)
    public Integer id;
    @ColumnInfo(name = "firstName")
    public String firstName;
    @ColumnInfo(name = "lastName")
    public String lastName;
    @ColumnInfo(name = "address")
    public String address;
    @ColumnInfo(name = "phoneNumber")
    public String phoneNumber;
    @ColumnInfo(name = "email")
    public String email;
    @ColumnInfo(name = "userName")
    public String userName;
    @ColumnInfo(name = "password")
    public String password;
    @ColumnInfo(name = "role_FK", index = true)
    public Integer role_FK;

    public User(User u) {
        this.id = u.getId();
        this.firstName = u.getFirstName();
        this.lastName = u.getLastName();
        this.address = u.getAddress();
        this.phoneNumber = u.getPhoneNumber();
        this.email = u.getEmail();
        this.userName = u.getUserName();
        this.password = u.getPassword();
        this.role_FK = u.getRole_FK();
    }

    public User(Integer id, String firstName, String lastName, String address, String phoneNumber, String email, String userName, String password, Integer role_FK) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.role_FK = role_FK;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole_FK() {
        return role_FK;
    }

    public void setRole_FK(Integer role_FK) {
        this.role_FK = role_FK;
    }
}
