package com.borg.model;

import android.content.Context;
import android.provider.ContactsContract;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Client.class, Invoice.class, MaterialConsumption.class, MaterialStock.class, Role.class, Task.class, User.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class DatabaseConnection extends RoomDatabase {

    public abstract ClientDao ClientDao();
    public abstract InvoiceDao InvoiceDao();
    public abstract MaterialConsumptionDao MaterialConsumptionDao();
    public abstract MaterialStockDao MaterialStockDao();
    public abstract RoleDao RoleDao();
    public abstract TaskDao TaskDao();
    public abstract UserDao UserDao();

    private static DatabaseConnection INSTANCE;

    public static DatabaseConnection getDbInstance(Context context){

        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseConnection.class, "BORG_DB")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    };


}
