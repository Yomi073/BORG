package com.borg.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.borg.model.database.Client;
import com.borg.model.database.ClientDao;
import com.borg.model.database.Invoice;
import com.borg.model.database.InvoiceDao;
import com.borg.model.database.MaterialConsumption;
import com.borg.model.database.MaterialConsumptionDao;
import com.borg.model.database.MaterialStock;
import com.borg.model.database.MaterialStockDao;
import com.borg.model.database.Role;
import com.borg.model.database.RoleDao;
import com.borg.model.database.Task;
import com.borg.model.database.TaskDao;
import com.borg.model.database.User;
import com.borg.model.database.UserDao;

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
    }


}
