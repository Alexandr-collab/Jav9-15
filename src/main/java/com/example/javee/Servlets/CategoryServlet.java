package com.example.javee.Servlets;

import com.example.javee.DAO.CategoryDAO;
import com.example.javee.DAO.Connection.ConnectionBuilder;
import com.example.javee.DAO.Connection.ConnectionProperty;
import com.example.javee.Models.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final CategoryDAO dao;
    public CategoryServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        dao = new CategoryDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories;
        try{
            categories = dao.findAll();
            request.setAttribute("categories", categories);
        }catch (Exception e){
            System.out.println(e);
        }
        request.getRequestDispatcher("views/category.jsp").forward(request,response);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            dao.insert(new Category(request.getParameter("categoryName"), request.getParameter("discription")));
        } catch (Exception e) {
            System.out.println(e);
        }
        doGet(request, response);
    }
}
