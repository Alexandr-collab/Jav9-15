package com.example.javee.DAO;

import com.example.javee.DAO.Connection.ConnectionBuilder;
import com.example.javee.DAO.Connection.DbConnectionBuilder;
import com.example.javee.Models.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CategoryDAO implements  RepositoryDAO<Category> {

    public CategoryDAO(){}

    private static final String select_all = "SELECT id, category_name, discription FROM category";
    private static final String select_ById = "SELECT id, category_name, discription FROM category WHERE id =?";
    private static final String insert = "INSERT INTO category(category_name, discription) VALUES(?, ?)";
    private static final String edit = "UPDATE category SET category_name = ?, discription = ? WHERE id = ? ";
    private static final String delete = "DELETE FROM category WHERE id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }
    // Добавление новой должности
    @Override
    public Long insert (Category category) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(insert, new String[] { "id" })) {
            long Id = -1L;
            pst.setString(1, category.getCategoryName());
            pst.setString(2, category.getDiscription());
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
    public void update(Category category) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(edit)) {
            pst.setString(1, category.getCategoryName());
            pst.setString(2, category.getDiscription());
            pst.setLong(3, category.getId());
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
    public Category findById(Long Id) {
        Category category = null;
        try (Connection con = getConnection()) {
            PreparedStatement pst =
                    con.prepareStatement(select_ById);
            pst.setLong(1, Id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                category = fillCategory(rs);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return category;
    }
    // Формирование списка всех должностей
    @Override
    public List<Category> findAll(){
        List<Category> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(select_all);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillCategory(rs));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    // Формирование класса Должности по результатам запроса к БД
    private Category fillCategory(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong("id"));
        category.setCategoryName(rs.getString("category_name"));
        category.setDiscription(rs.getString("discription"));
        return category;
    }
    // Поиск должности по id из списка должностей
    public Category FindById(Long id, List<Category> category) {
        if (category != null) {
            for (Category r : category) {
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
