package servlet.customer;

import dao.UserDao;
import model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customer/customerManagement")
public class customerManagement extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute("userId"); // 假设用户ID保存在Session中
        System.out.println(userId);
        User currentUser = userDao.find_ById(userId); // 假设有这个方法通过ID获取用户
        request.setAttribute("currentUser", currentUser);
        request.getRequestDispatcher("/customer/customerManagement.jsp").forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentUsername = request.getParameter("currentUsername"); // 现有用户名
        String newUsername = request.getParameter("username"); // 新用户名
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // 如果新用户名与当前用户名不同，则检查新用户名是否存在
        if (!newUsername.equals(currentUsername) && userDao.isUsernameExists(newUsername)) {
            request.setAttribute("error", "Update failed: Username already exists.");
            request.getRequestDispatcher("/customer/customerManagement.jsp").forward(request, response);
            return;
        }

        // 创建用户对象并更新
        User user = new User(newUsername, password, email);
        boolean result = userDao.update(user);

        if (result) {
            request.setAttribute("message", "Successfully updated");
        } else {
            request.setAttribute("error", "Update failed: Unknown error occurred.");
        }

        request.getRequestDispatcher("/customer/customerManagement.jsp").forward(request, response);
    }
}
