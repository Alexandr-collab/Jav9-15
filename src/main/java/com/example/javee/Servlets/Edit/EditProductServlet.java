package com.example.javee.Servlets.Edit;

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

@WebServlet("/editproduct")
public class EditProductServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final CategoryDAO categoryDAO;
    private final ProductDAO productDAO;

    public EditProductServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        categoryDAO = new CategoryDAO();
        productDAO = new ProductDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories;
        List<Product> products;
        String strId = request.getParameter("id");
        Long editProductId = (strId != null) ? Long.parseLong(strId) : null;
        Product editProduct;
        try {
            categories = categoryDAO.findAll();
            products = productDAO.findAll();
            for (Product ch:products){
                ch.setCategory(categoryDAO.FindById(ch.getCategoryId(), categories));
            }
            editProduct = productDAO.findById(editProductId);
            editProduct.setCategory(categoryDAO.findById(editProduct.getCategoryId()));
            categories.removeIf(category1 -> category1.getId() == editProduct.getCategoryId());
            request.setAttribute("categories", categories);
            request.setAttribute("products", products);
            request.setAttribute("productEdit", editProduct);
        } catch (Exception e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("/views/editproduct.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String strId = request.getParameter("id");
            Long editProductId = (strId != null) ? Long.parseLong(strId) : null;
            String product = request.getParameter("categoryField");
            int index1 = product.indexOf('=');
            int index2 = product.indexOf(",");
            String r1 = product.substring(index1+1, index2);
            long categoryId = Long.parseLong(r1.trim());
            Category category = categoryDAO.findById(categoryId);
            productDAO.update(new Product(editProductId, categoryId, request.getParameter("productName"), request.getParameter("unitPrice")
                    , request.getParameter("unitInStock") , request.getParameter("unitInOrder"),  category));
        } catch (Exception e) {
            System.out.println(e);

        }
        response.sendRedirect("/JavEE_war_exploded/products");
    }
}
