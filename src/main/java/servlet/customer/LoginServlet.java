package servlet.customer;

import dao.UserDao;
import model.User;
import dao.CartDao;
import model.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDao userDao;
    private CartDao cartDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
        cartDao = new CartDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 验证用户
        User user = userDao.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getId());
            session.setAttribute("userEmail", user.getEmail());

            // 创建购物车
            Cart cart = cartDao.find_ByUserId(user.getId());
            System.out.println(cart.getId() + "   " + cart.getUserId());
            if(cart == null) {
                System.out.println("No cart found for user ID: " + user.getId() + ", creating a new cart.");
                cart = cartDao.createCart(user.getId());
                if (cart != null) {
                    System.out.println("Cart created successfully for user ID: " + user.getId());
                } else {
                    System.err.println("Failed to create cart for user ID: " + user.getId());
                }
            }
            else {
                System.out.println("Cart found for user ID: " + user.getId());
            }
            session.setAttribute("cart", cart);

            // 根据用户角色重定向
            if ("admin".equals(user.getUserRole())) {
                response.sendRedirect(request.getContextPath() + "/admin/home.jsp"); // 管理员页面
            } else {
                response.sendRedirect(request.getContextPath() + "/customer/home.jsp"); // 顾客页面
            }
        } else {
            request.setAttribute("error", "Invalid username or password.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

}
