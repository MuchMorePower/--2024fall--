package servlet.admin;

import dao.OrderDao;
import dao.UserDao; // 确保有UserDao用于获取用户邮箱
import model.Order;
import util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/OrderDetail")
public class OrderDetailServlet extends HttpServlet {

    private OrderDao orderDao = new OrderDao();
    private UserDao userDao = new UserDao(); // 用户 DAO

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = orderDao.findById(orderId);

        request.setAttribute("order", order);
        request.getRequestDispatcher("/admin/OrderDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = orderDao.findById(orderId); // 获取订单信息

        if (order != null) {
            orderDao.delete(orderId); // 删除订单

            // 获取用户邮箱
            String userEmail = userDao.findEmailByUserId(order.getUserId());
            String subject = "Order Cancellation Notification";
            String body = "Dear User, your order (ID: " + orderId + ") has been successfully canceled.";
            EmailUtil.sendEmail(userEmail, subject, body);
        }

        request.setAttribute("message", "Order has been successfully canceled!"); // 添加提示信息
        response.sendRedirect(request.getContextPath() + "/admin/OrderList?message=Order has been successfully canceled"); // 重定向到订单列表
    }

}
