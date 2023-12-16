package com.example.javee.Models;

public class Product {
    private Long id, category_id;
    private String product_name, unit_price, unit_in_stock, unit_in_order;
    private Category category;

    public Product() {
    }

    public Product(Long id, Long category_id, String product_name, String unit_price, String unit_in_stock, String unit_in_order, Category category) {
        this.id = id;
        this.category_id = category_id;
        this.product_name = product_name;
        this.unit_price = unit_price;
        this.unit_in_stock = unit_in_stock;
        this.unit_in_order = unit_in_order;
        this.category = category;
    }

    public Product(Long category_id, String product_name, String unit_price, String unit_in_stock, String unit_in_order, Category category) {
        this.category_id = category_id;
        this.product_name = product_name;
        this.unit_price = unit_price;
        this.unit_in_stock = unit_in_stock;
        this.unit_in_order = unit_in_order;
        this.category = category;
    }

    public Product(String product_name, String unit_price, String unit_in_stock, String unit_in_order, Category category) {
        this.product_name = product_name;
        this.unit_price = unit_price;
        this.unit_in_stock = unit_in_stock;
        this.unit_in_order = unit_in_order;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public Long getCategoryId() {
        return category_id;
    }

    public String getProductName() {
        return product_name;
    }

    public String getUnitPrice() {
        return unit_price;
    }

    public String getUnitInStock() {
        return unit_in_stock;
    }

    public String getUnitInOrder() {
        return unit_in_order;
    }

    public Category getCategory() {
        return category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategoryId(Long category_Id) {
        this.category_id = category_Id;
    }

    public void setProductName(String product_Name) {
        this.product_name = product_Name;
    }

    public void setUnitPrice(String unit_Price) {
        this.unit_price = unit_Price;
    }

    public void setUnitInStock(String unit_In_Stock) {
        this.unit_in_stock = unit_In_Stock;
    }

    public void setUnitInOrder(String unit_In_Order) {
        this.unit_in_order = unit_In_Order;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", categoryId=" + category_id +
                ", productName='" + product_name + '\'' +
                ", unitPrice='" + unit_price + '\'' +
                ", unitInStock='" + unit_in_stock + '\'' +
                ", UnitInOrder='" + unit_in_order + '\'' +
                ", category=" + category +
                '}';
    }
}
