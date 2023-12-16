package com.example.javee.Servlets;

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

@WebServlet("/orderdetail")
public class OrderDetailServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final ProductDAO productDAO;
    private final OrderDAO orderDAO;
    private final OrderDetailDAO orderDetailDAO;
    public OrderDetailServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        productDAO = new ProductDAO();
        orderDAO = new OrderDAO();
        orderDetailDAO = new OrderDetailDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products;
        List<Orders> orders;
        List<OrderDetail> orderDetails;
        try{
            products = productDAO.findAll();
            orders = orderDAO.findAll();
            orderDetails = orderDetailDAO.findAll();
            request.setAttribute("orderDetails", orderDetails);
            request.setAttribute("orders", orders);
            request.setAttribute("products", products);
            for(OrderDetail od: orderDetails){
                od.setProduct(productDAO.FindById(od.getProductId(), products));
                od.setOrders(orderDAO.FindById(od.getOrderId(), orders));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        request.getRequestDispatcher("views/orderdetail.jsp").forward(request,response);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            String product = request.getParameter("product");
            int index1 = product.indexOf('=');
            int index2 = product.indexOf(",");
            String r1 = product.substring(index1+1, index2);
            long productId = Long.parseLong(r1.trim());
            Product products = productDAO.findById(productId);

            String order = request.getParameter("order");
            int index3 = order.indexOf('=');
            int index4 = order.indexOf(",");
            String r2 = order.substring(index3+1, index4);
            long orderId = Long.parseLong(r2.trim());
            Orders orders = orderDAO.findById(orderId);

            orderDetailDAO.insert(new OrderDetail(productId, orderId, request.getParameter("unitPrice"), request.getParameter("discount"), products, orders));
        }catch (Exception e){
            System.out.println(e);
        }
        doGet(request, response);
    }
}
