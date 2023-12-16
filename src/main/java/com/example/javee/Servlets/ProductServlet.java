package com.example.javee.Servlets;

import com.example.javee.DAO.CategoryDAO;
import com.example.javee.DAO.Connection.ConnectionProperty;
import com.example.javee.DAO.ProductDAO;
import com.example.javee.Models.Category;
import com.example.javee.Models.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final CategoryDAO categoryDAO;

    private final ProductDAO productDAO;

    public ProductServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        categoryDAO = new CategoryDAO();
        productDAO = new ProductDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories;
        List<Product> products;
        try{
            categories = categoryDAO.findAll();
            products = productDAO.findAll();
            request.setAttribute("categories", categories);
            request.setAttribute("products", products);
            for (Product pr:products){
                pr.setCategory(categoryDAO.FindById(pr.getCategoryId(), categories));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        request.getRequestDispatcher("views/product.jsp").forward(request,response);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
           String category = request.getParameter("category");
           int ind1 = category.indexOf('=');
           int ind2 = category.indexOf(',');
           String r1 = category.substring(ind1+1, ind2);
           long categoryId = Long.parseLong(r1.trim());
           Category categories = categoryDAO.findById(categoryId);
           productDAO.insert(new Product(categoryId, request.getParameter("productName"), request.getParameter("unitPrice"),
                   request.getParameter("unitInStock"), request.getParameter("unitInOrder"), categories));
        }catch (Exception e){
            System.out.println(e);
        }
        doGet(request, response);
    }
}
