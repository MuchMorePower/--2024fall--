package servlet.customer;

import dao.ProductDao;
import dao.CartDao;
import dao.CartItemDao;
import dao.BrowseHistoryDao;
import model.Product;
import model.Cart;
import model.CartItem;
import model.BrowseHistory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/customer/productDetail")
public class ProductDetailServlet extends HttpServlet {
    private ProductDao productDao = new ProductDao();
    private BrowseHistoryDao browseHistoryDao = new BrowseHistoryDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));
        Product product = productDao.findById(productId);

        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        // 记录用户浏览历史
        BrowseHistory browseHistory = new BrowseHistory();
        browseHistory.setUserId(userId);
        browseHistory.setProductId(productId);
        browseHistory.setBrowseTime(new Date());
        browseHistoryDao.save(browseHistory);

        request.setAttribute("product", product);

        // 获取并设置成功消息
        String successMessage = (String) request.getAttribute("successMessage");
        request.setAttribute("successMessage", successMessage);

        request.getRequestDispatcher("/customer/productDetail.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            int userId = (Integer) session.getAttribute("userId"); // 获取用户ID
            cart = new CartDao().createNewCart(userId); // 创建购物车
            session.setAttribute("cart", cart);
        }

        CartItem item = new CartItem();
        item.setCartId(cart.getId());
        item.setProductId(productId);
        item.setQuantity(quantity);

        cart.addItem(item);

        // 设置成功消息
        request.setAttribute("successMessage", "Add to Cart Success!");

        // 重新调用 doGet 方法
        doGet(request, response);
    }
}
