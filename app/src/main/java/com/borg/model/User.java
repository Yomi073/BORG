package com.borg.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "user", foreignKeys = @ForeignKey(entity = Role.class, parentColumns = "id",childColumns = "role_FK", onDelete = 5))
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", index = true)
    public int id;
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
    public int role_FK;



}
