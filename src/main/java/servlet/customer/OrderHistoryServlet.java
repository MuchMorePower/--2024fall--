package servlet.customer;

import model.Order;
import dao.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/customer/orderHistory")
public class OrderHistoryServlet extends HttpServlet {

    private OrderDao orderDao = new OrderDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId"); // 从 session 获取用户 ID

        // 获取用户的历史订单
        List<Order> orders = orderDao.findByUserId(userId);
        request.setAttribute("orders", orders); // 将订单列表设置到请求中

        // 转发到订单历史 JSP 页面
        request.getRequestDispatcher("/customer/orderHistory.jsp").forward(request, response);
    }
}
