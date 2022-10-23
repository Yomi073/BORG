package com.borg.model.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "invoice")
public class Invoice {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", index = true)
    public Integer id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "quantity")
    public Double quantity;
    @ColumnInfo(name = "sellingPrice")
    public Double sellingPrice;
    @ColumnInfo(name = "sum")
    public Double sum;

    public Invoice(Invoice invoice) {
        this.id = invoice.getId();
        this.name = invoice.getName();
        this.quantity = invoice.getQuantity();
        this.sellingPrice = invoice.getSellingPrice();
        this.sum = invoice.getSum();
    }

    public Invoice(Integer id, String name, Double quantity, Double sellingPrice, Double sum) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.sum = sum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
