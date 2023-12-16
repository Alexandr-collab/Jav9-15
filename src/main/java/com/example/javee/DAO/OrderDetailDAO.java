package com.example.javee.DAO;

import com.example.javee.DAO.Connection.ConnectionBuilder;
import com.example.javee.DAO.Connection.DbConnectionBuilder;
import com.example.javee.Models.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class OrderDetailDAO implements RepositoryDAO<OrderDetail> {

    public OrderDetailDAO(){}

    private static final String select_all = "SELECT id, product_id, order_id, unit_price, discount FROM order_detail";
    private static final String select_ById = "SELECT id, product_id, order_id, unit_price, discount FROM order_detail WHERE id =?";
    private static final String insert = "INSERT INTO order_detail(product_id, order_id, unit_price, discount) VALUES(?, ?, ?, ?)";
    private static final String edit = "UPDATE order_detail SET product_id = ? , order_id = ? , unit_price = ? , discount = ? WHERE id = ? ";
    private static final String delete = "DELETE FROM order_detail WHERE id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }
    // Добавление новой должности
    @Override
    public Long insert (OrderDetail orderDetail) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(insert, new String[] { "id" })) {
            long Id = -1L;
            pst.setLong(1, orderDetail.getProductId());
            pst.setLong(2, orderDetail.getOrderId());
            pst.setString(3, orderDetail.getUnitPrice());
            pst.setString(4, orderDetail.getDiscount());
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
    public void update(OrderDetail orderDetail) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(edit)) {
            pst.setLong(1, orderDetail.getProductId());
            pst.setLong(2, orderDetail.getOrderId());
            pst.setString(3, orderDetail.getUnitPrice());
            pst.setString(4, orderDetail.getDiscount());
            pst.setLong(5, orderDetail.getId());
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
    public OrderDetail findById(Long Id) {
        OrderDetail orderDetail = null;
        try (Connection con = getConnection()) {
            PreparedStatement pst =
                    con.prepareStatement(select_ById);
            pst.setLong(1, Id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                orderDetail = fillDetail(rs);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return orderDetail;
    }
    // Формирование списка всех должностей
    @Override
    public List<OrderDetail> findAll(){
        List<OrderDetail> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(select_all);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillDetail(rs));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    // Формирование класса Должности по результатам запроса к БД
    private OrderDetail fillDetail(ResultSet rs) throws SQLException {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(rs.getLong("id"));
        orderDetail.setProductId(rs.getLong("product_id"));
        orderDetail.setOrderId(rs.getLong("order_id"));
        orderDetail.setUnitPrice(rs.getString("unit_price"));
        orderDetail.setDiscount(rs.getString("discount"));
        return orderDetail;
    }
    // Поиск должности по id из списка должностей
    public OrderDetail FindById(Long id, List<OrderDetail> orderDetails) {
        if (orderDetails != null) {
            for (OrderDetail r : orderDetails) {
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
