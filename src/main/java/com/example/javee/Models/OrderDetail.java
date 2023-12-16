package com.example.javee.Models;

public class OrderDetail {
    private Long id, product_id, order_id;
    private String unit_price, discount;
    private Product product;
    private Orders orders;

    public OrderDetail() {
    }

    public OrderDetail(Long id, Long product_id, Long order_id, String unit_price, String discount, Product product, Orders orders) {
        this.id = id;
        this.product_id = product_id;
        this.order_id = order_id;
        this.unit_price = unit_price;
        this.discount = discount;
        this.product = product;
        this.orders = orders;
    }

    public OrderDetail(Long product_id, Long order_id, String unit_price, String discount, Product product, Orders orders) {
        this.product_id = product_id;
        this.order_id = order_id;
        this.unit_price = unit_price;
        this.discount = discount;
        this.product = product;
        this.orders = orders;
    }

    public OrderDetail(String unit_price, String discount, Product product, Orders orders) {
        this.unit_price = unit_price;
        this.discount = discount;
        this.product = product;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return product_id;
    }

    public Long getOrderId() {
        return order_id;
    }

    public String getUnitPrice() {
        return unit_price;
    }

    public String getDiscount() {
        return discount;
    }

    public Product getProduct() {
        return product;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductId(Long product_Id) {
        this.product_id = product_Id;
    }

    public void setOrderId(Long order_Id) {
        this.order_id = order_Id;
    }

    public void setUnitPrice(String unit_Price) {
        this.unit_price = unit_Price;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", productId=" + product_id +
                ", orderId=" + order_id +
                ", unitPrice='" + unit_price + '\'' +
                ", discount='" + discount + '\'' +
                ", product=" + product +
                ", orders=" + orders +
                '}';
    }
}
