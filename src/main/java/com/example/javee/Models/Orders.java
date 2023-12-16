package com.example.javee.Models;

public class Orders {
    private Long id;
    private String customer, employee, order_date, ship_date, ship_name, ship_address;

    public Orders() {
    }

    public Orders(Long id, String customer, String employee, String order_date, String ship_date, String ship_name, String ship_address) {
        this.id = id;
        this.customer = customer;
        this.employee = employee;
        this.order_date = order_date;
        this.ship_date = ship_date;
        this.ship_name = ship_name;
        this.ship_address = ship_address;
    }

    public Orders(String customer, String employee, String order_date, String ship_date, String ship_name, String ship_address) {
        this.customer = customer;
        this.employee = employee;
        this.order_date = order_date;
        this.ship_date = ship_date;
        this.ship_name = ship_name;
        this.ship_address = ship_address;
    }

    public Long getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public String getEmployee() {
        return employee;
    }

    public String getOrderDate() {
        return order_date;
    }

    public String getShipDate() {
        return ship_date;
    }

    public String getShipName() {
        return ship_name;
    }

    public String getShipAddress() {
        return ship_address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public void setOrderDate(String order_Date) {
        this.order_date = order_Date;
    }

    public void setShipDate(String ship_Date) {
        this.ship_date = ship_Date;
    }

    public void setShipName(String ship_Name) {
        this.ship_name = ship_Name;
    }

    public void setShipAddress(String ship_Address) {
        this.ship_address = ship_Address;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", customer='" + customer + '\'' +
                ", employee='" + employee + '\'' +
                ", orderDate='" + order_date + '\'' +
                ", shipDate='" + ship_date + '\'' +
                ", shipName='" + ship_name + '\'' +
                ", shipAddress='" + ship_address + '\'' +
                '}';
    }
}
