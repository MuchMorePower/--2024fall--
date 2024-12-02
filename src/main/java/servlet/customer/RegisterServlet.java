package servlet.customer;

import dao.UserDao;
import model.User;
import model.Cart;
import dao.CartDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDao userDao;
    private CartDao cartDao; // 声明 CartDao

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
        cartDao = new CartDao(); // 初始化 CartDao
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // 检查用户名是否已存在
        if (userDao.isUsernameExists(username)) {
            request.setAttribute("error", "Username already exists.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return; // 返回以防止继续执行
        }

        User user = new User(username, password, email);
        boolean result = userDao.registerUser(user);

        if (result) {
            // 创建购物车并将其与用户关联
            Cart cart = cartDao.createCart(user.getId());
            System.out.println(cart.getId() + "   " + cart.getUserId());
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            request.setAttribute("error", "Registration failed.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}

