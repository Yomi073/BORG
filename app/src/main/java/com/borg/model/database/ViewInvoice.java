package com.borg.model.database;

public class ViewInvoice {

    private Integer indeks;
    private Integer id_on_stock;
    private Integer id_task;
    private String material_name;
    private Double quantity_on_stock;
    private Double quantity_on_invoice;
    private Double purchasePrice;
    private Double sellingPrice;
    private Double sum;

    public ViewInvoice(Integer indeks, Integer id_on_stock, String material_name, Double quantity_on_stock, Double quantity_on_invoice, Double purchasePrice, Double sellingPrice, Integer id_task, Double sum) {
        this.indeks = indeks;
        this.id_on_stock = id_on_stock;
        this.material_name = material_name;
        this.quantity_on_stock = quantity_on_stock;
        this.quantity_on_invoice = quantity_on_invoice;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.id_task = id_task;
        this.sum = sum;
    }

    public Integer getIndeks() {
        return indeks;
    }

    public void setIndeks(Integer indeks) {
        this.indeks = indeks;
    }

    public Integer getId_on_stock() {
        return id_on_stock;
    }

    public void setId_on_stock(Integer id_on_stock) {
        this.id_on_stock = id_on_stock;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public Double getQuantity_on_stock() {
        return quantity_on_stock;
    }

    public void setQuantity_on_stock(Double quantity_on_stock) {
        this.quantity_on_stock = quantity_on_stock;
    }

    public Double getQuantity_on_invoice() {
        return quantity_on_invoice;
    }

    public void setQuantity_on_invoice(Double quantity_on_invoice) {
        this.quantity_on_invoice = quantity_on_invoice;
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

    public Integer getId_task() {
        return id_task;
    }

    public void setId_task(Integer id_task) {
        this.id_task = id_task;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
