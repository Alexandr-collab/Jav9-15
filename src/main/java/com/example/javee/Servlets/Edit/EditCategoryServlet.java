package com.example.javee.Servlets.Edit;

import com.example.javee.DAO.CategoryDAO;
import com.example.javee.DAO.Connection.ConnectionProperty;
import com.example.javee.Models.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.checkerframework.checker.units.qual.C;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Struct;
import java.util.List;

@WebServlet("/editcategory")
public class EditCategoryServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final CategoryDAO dao;

    public EditCategoryServlet() throws FileNotFoundException, IOException{
        super();
        prop = new ConnectionProperty();
        dao = new CategoryDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        List<Category> categories;
        try{
            categories = dao.findAll();
            request.setAttribute("categories", categories);
        }catch (Exception e){
            System.out.println(e);
        }
        String strId = request.getParameter("id");
        Long editCategoryId = (strId != null) ? Long.parseLong(strId) : null;
        Category editCategory;
        try{
            editCategory = dao.findById(editCategoryId);
            request.setAttribute("categoryEdit", editCategory);
        } catch (Exception e){
            System.out.println(e);
        }
        request.getRequestDispatcher("/views/editcategory.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String strId = request.getParameter("id");
        Long editCategoryId = (strId != null) ? Long.parseLong(strId) : null;
        Category updateCategory = new Category(editCategoryId, request.getParameter("categoryName"), request.getParameter("discription"));
        try{
            dao.update(updateCategory);
        }catch (Exception e){
            System.out.println(e);
        }
        response.sendRedirect("/JavEE_war_exploded/category");
    }
}
