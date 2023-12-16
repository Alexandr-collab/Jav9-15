package com.example.javee.Servlets.Delete;

import com.example.javee.DAO.Connection.ConnectionProperty;
import com.example.javee.DAO.OrderDAO;
import com.example.javee.DAO.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteproduct")
public class DeleteProductServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final ProductDAO dao;

    public DeleteProductServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        dao = new ProductDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        Long deleteid = (strId != null) ? Long.parseLong(strId) : null;
        try {
            dao.delete(deleteid);
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("/JavEE_war_exploded/products");
    }
}
