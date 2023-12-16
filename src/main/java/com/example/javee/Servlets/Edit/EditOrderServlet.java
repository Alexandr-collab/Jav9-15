package com.example.javee.Servlets.Edit;

import com.example.javee.DAO.Connection.ConnectionProperty;
import com.example.javee.DAO.OrderDAO;
import com.example.javee.Models.Orders;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@WebServlet("/editorder")
public class EditOrderServlet extends HttpServlet {

    private final ConnectionProperty prop;
    private final OrderDAO dao;

    public EditOrderServlet() throws FileNotFoundException, IOException {
        super();
        prop = new ConnectionProperty();
        dao = new OrderDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        List<Orders> orders;
        try {
            orders = dao.findAll();
            request.setAttribute("orders", orders);
        } catch (Exception e) {
            System.out.println(e);
        }
        String strId = request.getParameter("id");
        Long editOrderId = (strId != null) ? Long.parseLong(strId) : null;
        Orders editOrder;
        try {
            editOrder= dao.findById(editOrderId);
            request.setAttribute("orderEdit", editOrder);
        } catch (Exception e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("/views/editorder.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        Long editOrderId = (strId != null) ? Long.parseLong(strId) : null;
        Orders updateOrder = new Orders(editOrderId, request.getParameter("customer"), request.getParameter("employee"),
                request.getParameter("orderDate"), request.getParameter("shipDate"), request.getParameter("shipName"),
                request.getParameter("shipAddress"));
        try {
            dao.update(updateOrder);
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("/JavEE_war_exploded/orders");
    }
}