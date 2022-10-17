package com.borg.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "client")
public class Client {

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

}
