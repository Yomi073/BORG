package com.borg.model.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "materialstock")
public class MaterialStock {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", index = true)
    public Integer id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "quantity")
    public Double quantity;
    @ColumnInfo(name = "purchasePrice")
    public Double purchasePrice;
    @ColumnInfo(name = "sellingPrice")
    public Double sellingPrice;

    public MaterialStock(MaterialStock materialStock) {
        this.id = materialStock.getId();
        this.name = materialStock.getName();
        this.quantity = materialStock.getQuantity();
        this.purchasePrice = materialStock.getPurchasePrice();
        this.sellingPrice = materialStock.getSellingPrice();
    }

    public MaterialStock(Integer id, String name, Double quantity, Double purchasePrice, Double sellingPrice) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
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

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
