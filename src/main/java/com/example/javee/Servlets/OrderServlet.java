package com.example.javee.Servlets;

import com.example.javee.DAO.Connection.ConnectionProperty;
import com.example.javee.DAO.OrderDAO;
import com.example.javee.Models.Category;
import com.example.javee.Models.Orders;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final OrderDAO dao;
    public OrderServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        dao = new OrderDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Orders> orders;
        try{
            orders = dao.findAll();
            request.setAttribute("orders", orders);
        }catch (Exception e){
            System.out.println(e);
        }
        request.getRequestDispatcher("views/orders.jsp").forward(request,response);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            dao.insert(new Orders(request.getParameter("customer"), request.getParameter("employee"),
                    request.getParameter("orderDate"), request.getParameter("shipDate"),
                    request.getParameter("shipName"), request.getParameter("shipAddress")));
        }catch (Exception e){
            System.out.println(e);
        }
        doGet(request, response);
    }
}
