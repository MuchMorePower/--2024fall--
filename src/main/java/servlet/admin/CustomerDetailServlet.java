package servlet.admin;

import dao.BrowseHistoryDao;
import dao.OrderDao;
import dao.UserDao;
import model.BrowseHistory;
import model.Order;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/CustomerDetail")
public class CustomerDetailServlet extends HttpServlet {
    private UserDao userDao = new UserDao();
    private OrderDao orderDao = new OrderDao();
    private BrowseHistoryDao browseHistoryDao = new BrowseHistoryDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId")); // 从请求中获取用户ID

        // 查询用户信息
        User user = userDao.findById(userId);
        request.setAttribute("user", user);

        // 查询用户的所有订单
        List<Order> orders = orderDao.findByUserId(userId);
        request.setAttribute("orders", orders);

        // 查询用户的所有浏览记录
        List<BrowseHistory> histories = browseHistoryDao.findByUserId(userId);
        request.setAttribute("histories", histories);

        // 转发到 JSP 页面
        request.getRequestDispatcher("/admin/CustomerDetail.jsp").forward(request, response);
    }
}
