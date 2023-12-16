package com.example.javee.Servlets.Edit;

import com.example.javee.DAO.Connection.ConnectionProperty;
import com.example.javee.DAO.OrderDAO;
import com.example.javee.DAO.OrderDetailDAO;
import com.example.javee.DAO.ProductDAO;
import com.example.javee.Models.OrderDetail;
import com.example.javee.Models.Orders;
import com.example.javee.Models.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/editorderdetail")
public class EditOrderDetailServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final ProductDAO productDAO;
    private final OrderDAO orderDAO;
    private final OrderDetailDAO orderDetailDAO;

    public EditOrderDetailServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        productDAO = new ProductDAO();
        orderDAO = new OrderDAO();
        orderDetailDAO = new OrderDetailDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products;
        List<Orders> orders;
        List<OrderDetail> orderDetails;

        String strId = request.getParameter("id");
        Long editOrderId = (strId != null) ? Long.parseLong(strId) : null;
        OrderDetail editOD;
        try {
            orders = orderDAO.findAll();
            products = productDAO.findAll();
            orderDetails = orderDetailDAO.findAll();
            for (OrderDetail od:orderDetails){
                od.setOrders(orderDAO.FindById(od.getOrderId(), orders));
                od.setProduct(productDAO.FindById(od.getProductId(), products));
            }

            editOD = orderDetailDAO.findById(editOrderId);
            editOD.setOrders(orderDAO.findById(editOD.getOrderId()));
            products.removeIf(order -> order.getId() == editOD.getOrderId());
            editOD.setProduct(productDAO.findById(editOD.getProductId()));
            orders.removeIf(prod -> prod.getId() == editOD.getProductId());
            request.setAttribute("products", products);
            request.setAttribute("orders", orders);
            request.setAttribute("orderDetails", orderDetails);
            request.setAttribute("odEdit", editOD);
        } catch (Exception e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("/views/editorderdetail.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String strId = request.getParameter("id");
            Long editOrderDetailId = (strId != null) ? Long.parseLong(strId) : null;

            String prod = request.getParameter("productField");
            int index1 = prod.indexOf('=');
            int index2 = prod.indexOf(",");
            String r1 = prod.substring(index1+1, index2);
            long prodId = Long.parseLong(r1.trim());
            Product product = productDAO.findById(prodId);

            String order = request.getParameter("orderField");
            int index3 = order.indexOf('=');
            int index4 = order.indexOf(",");
            String r2 = order.substring(index3+1, index4);
            long ordId = Long.parseLong(r2.trim());
            Orders orders = orderDAO.findById(ordId);

            orderDetailDAO.update(new OrderDetail(editOrderDetailId, prodId, ordId, request.getParameter("unitPrice"),
                    request.getParameter("discount"), product, orders));
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("/JavEE_war_exploded/orderdetail");
    }
}
