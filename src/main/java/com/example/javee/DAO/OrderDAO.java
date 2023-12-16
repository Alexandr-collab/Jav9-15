package com.example.javee.DAO;

import com.example.javee.DAO.Connection.ConnectionBuilder;
import com.example.javee.DAO.Connection.DbConnectionBuilder;
import com.example.javee.Models.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class OrderDAO implements RepositoryDAO<Orders> {
    public OrderDAO(){}

    private static final String select_all = "SELECT id, customer, employee, order_date, ship_date, ship_name, ship_address FROM orders";
    private static final String select_ById = "SELECT id, customer, employee, order_date, ship_date, ship_name, ship_address FROM orders WHERE id =?";
    private static final String insert = "INSERT INTO orders(customer, employee, order_date, ship_date, ship_name, ship_address) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String edit = "UPDATE orders SET customer= ?, employee= ?, order_date= ?, ship_date= ?, ship_name= ?, ship_address = ? WHERE id = ? ";
    private static final String delete = "DELETE FROM orders WHERE id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }
    // Добавление новой должности
    @Override
    public Long insert (Orders orders) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(insert, new String[] { "id" })) {
            long Id = -1L;
            pst.setString(1, orders.getCustomer());
            pst.setString(2, orders.getEmployee());
            pst.setString(3, orders.getOrderDate());
            pst.setString(4, orders.getShipDate());
            pst.setString(5, orders.getShipName());
            pst.setString(6, orders.getShipAddress());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                Id = gk.getLong("id");
            }
            gk.close();
            return Id;
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1L;
    }
    // Редактирование должности
    @Override
    public void update(Orders orders) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(edit)) {
            pst.setString(1, orders.getCustomer());
            pst.setString(2, orders.getEmployee());
            pst.setString(3, orders.getOrderDate());
            pst.setString(4, orders.getShipDate());
            pst.setString(5, orders.getShipName());
            pst.setString(6, orders.getShipAddress());
            pst.setLong(7, orders.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Удаление должности
    @Override
    public void delete(Long Id) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(delete)) {
            pst.setLong(1, Id);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Поиск должности по Id
    @Override
    public Orders findById(Long Id) {
        Orders orders = null;
        try (Connection con = getConnection()) {
            PreparedStatement pst =
                    con.prepareStatement(select_ById);
            pst.setLong(1, Id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                orders = fillOrders(rs);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return orders;
    }
    // Формирование списка всех должностей
    @Override
    public List<Orders> findAll(){
        List<Orders> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(select_all);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillOrders(rs));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    // Формирование класса Должности по результатам запроса к БД
    private Orders fillOrders(ResultSet rs) throws SQLException {
        Orders orders = new Orders();
        orders.setId(rs.getLong("id"));
        orders.setCustomer(rs.getString("customer"));
        orders.setEmployee(rs.getString("employee"));
        orders.setOrderDate(rs.getString("order_date"));
        orders.setShipDate(rs.getString("ship_date"));
        orders.setShipName(rs.getString("ship_name"));
        orders.setShipAddress(rs.getString("ship_address"));
        return orders;
    }
    // Поиск должности по id из списка должностей
    public Orders FindById(Long id, List<Orders> orders) {
        if (orders != null) {
            for (Orders r : orders) {
                if ((r.getId()).equals(id)) {
                    return r;
                }
            }
        } else {
            return null;
        }
        return null;
    }
}
