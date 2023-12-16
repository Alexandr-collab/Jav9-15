package com.example.javee.DAO;

import com.example.javee.DAO.Connection.ConnectionBuilder;
import com.example.javee.DAO.Connection.DbConnectionBuilder;
import com.example.javee.Models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductDAO implements RepositoryDAO<Product> {

    public ProductDAO(){}

    private static final String select_all = "SELECT id, category_id, product_name, unit_price, unit_in_stock, unit_in_order FROM product";
    private static final String select_ById = "SELECT id, category_id, product_name, unit_price, unit_in_stock, unit_in_order FROM product WHERE id =?";
    private static final String insert = "INSERT INTO product(category_id, product_name, unit_price, unit_in_stock, unit_in_order) VALUES(?, ?, ?, ?, ?)";
    private static final String edit = "UPDATE product SET category_id= ?, product_name= ?, unit_price= ?, unit_in_stock= ?, unit_in_order = ? WHERE id = ? ";
    private static final String delete = "DELETE FROM product WHERE id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }
    // Добавление новой должности
    @Override
    public Long insert (Product product) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(insert, new String[] { "id" })) {
            long Id = -1L;
            pst.setLong(1, product.getCategoryId());
            pst.setString(2, product.getProductName());
            pst.setString(3, product.getUnitPrice());
            pst.setString(4, product.getUnitInStock());
            pst.setString(5, product.getUnitInOrder());
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
    public void update(Product product) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(edit)) {
            pst.setLong(1, product.getCategoryId());
            pst.setString(2, product.getProductName());
            pst.setString(3, product.getUnitPrice());
            pst.setString(4, product.getUnitInStock());
            pst.setString(5, product.getUnitInOrder());
            pst.setLong(6, product.getId());
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
    public Product findById(Long Id) {
        Product product = null;
        try (Connection con = getConnection()) {
            PreparedStatement pst =
                    con.prepareStatement(select_ById);
            pst.setLong(1, Id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                product = fillProduct(rs);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return product;
    }
    // Формирование списка всех должностей
    @Override
    public List<Product> findAll(){
        List<Product> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(select_all);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillProduct(rs));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    // Формирование класса Должности по результатам запроса к БД
    private Product fillProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setCategoryId(rs.getLong("category_id"));
        product.setProductName(rs.getString("product_name"));
        product.setUnitPrice(rs.getString("unit_price"));
        product.setUnitInStock(rs.getString("unit_in_stock"));
        product.setUnitInOrder(rs.getString("unit_in_order"));
        return product;
    }
    // Поиск должности по id из списка должностей
    public Product FindById(Long id, List<Product> products) {
        if (products != null) {
            for (Product r : products) {
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
