package servlet.customer;

import dao.CartDao;
import dao.CartItemDao;
import model.Cart;
import model.CartItem;
import model.Product;
import dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/customer/cart")
public class CartServlet extends HttpServlet {
    private CartDao cartDao = new CartDao();
    private CartItemDao cartItemDao = new CartItemDao();
    private ProductDao productDao = new ProductDao();
    private int  cartId;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute("userId"); // 假设用户ID保存在Session中
        Cart cart = cartDao.findByUserId(userId); // 获取用户的购物车
        List<CartItem> cartItems = cartItemDao.getCartItems(cart.getId()); // 获取购物车中的商品项
        double totalPrice = cartItemDao.calculateTotalPrice(cart.getId()); // 计算总价

        request.setAttribute("cartItems", cartItems);
        request.setAttribute("totalPrice", totalPrice);
        request.getRequestDispatcher("/customer/cart.jsp").forward(request, response); // 转发到购物车页面
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart != null) {
            cartId = cart.getId();
        }

        switch (action) {
            case "add":
                int productId = Integer.parseInt(request.getParameter("productId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                Product product = productDao.findByProductId(productId); // 根据ID获取商品
                CartItem cartItem = new CartItem();
                cartItem.setCartId(cartId);
                cartItem.setProductId(productId);
                cartItem.setPrice(product.getPrice()); // 设置价格
                cartItem.setQuantity(quantity);
                cartItemDao.addCartItem(cartItem); // 添加商品到购物车
                break;

            case "remove":
                productId = Integer.parseInt(request.getParameter("productId"));
                cartItemDao.removeCartItem(productId); // 从购物车移除商品
                break;

            case "update":
                productId = Integer.parseInt(request.getParameter("productId"));
                quantity = Integer.parseInt(request.getParameter("quantity"));
                cartItemDao.updateCartItem(productId, quantity); // 更新商品数量
                break;
        }

        response.sendRedirect(request.getContextPath() + "/customer/cart");// 重定向回购物车
    }
}
