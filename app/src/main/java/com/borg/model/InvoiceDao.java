package com.borg.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface InvoiceDao {

    @Insert
    void createInvoice(Invoice invoice);

    @Query("SELECT * FROM invoice")
    List<Invoice> readAllInvoice();

    @Update
    void update(Invoice invoice);

    @Query("DELETE FROM invoice")
    void deleteAllInvoice();

    @Delete
    void delete(Invoice invoice);

}
