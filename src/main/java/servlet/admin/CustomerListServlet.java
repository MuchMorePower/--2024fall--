package servlet.admin;

import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/CustomerList")
public class CustomerListServlet extends HttpServlet {
    private UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> customers = userDao.findAllCustomers(); // 获取所有客户
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/admin/CustomerList.jsp").forward(request, response);
    }
}
