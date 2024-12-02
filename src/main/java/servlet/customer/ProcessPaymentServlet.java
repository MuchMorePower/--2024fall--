package servlet.customer;

import model.Cart;
import model.CartItem;
import model.Order;
import model.OrderItem;
import dao.CartDao;
import dao.CartItemDao;
import dao.OrderDao;
import dao.OrderItemDao;
import dao.ProductDao;
import model.Product;
import util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/processPayment")
public class ProcessPaymentServlet extends HttpServlet {

    private OrderDao orderDao = new OrderDao();
    private OrderItemDao orderItemDao = new OrderItemDao();
    private CartDao cartDao = new CartDao();
    private CartItemDao cartItemDao = new CartItemDao();
    private ProductDao productDao = new ProductDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        Cart cart = cartDao.find_ByUserId(userId);

        if (cart != null) {
            // 创建订单并设置状态为“进行中”
            Order order = new Order();
            order.setUserId(userId);
            order.setStatus("Unpaid"); // 设定初始状态为“进行中”


            // 保存订单项
            List<CartItem> cartItems = cartItemDao.getAllCartItems(cart.getId());
            double total_price = 0 ;
            for (CartItem cartItem : cartItems) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderId(order.getId());
                orderItem.setProductId(cartItem.getProductId());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setPrice(cartItem.getQuantity() * cartItem.getPrice()); // 假设有 getPrice() 方法
                total_price += cartItem.getPrice() * cartItem.getQuantity();
                productDao.decreaseStock(cartItem.getProductId(), cartItem.getQuantity());
                orderItemDao.save(orderItem);
            }

            order.setTotal(total_price); // 从购物车获取总价
            order.setStatus("Paid");
            orderDao.save(order); // 保存订单

            // 清空购物车
            cartItemDao.clearCartItems(cart.getId());


            // 发送邮件
            String userEmail = (String) session.getAttribute("userEmail"); // 假设用户邮箱保存在 session 中
            String subject = "Order Confirmation";
            String body = "Thank you for your purchase! Your order ID is " + order.getId() + "。";
            EmailUtil.sendEmail(userEmail, subject, body);

            // 显示支付成功页面
            request.setAttribute("orderId", order.getId());
            request.getRequestDispatcher("/customer/paymentSuccess.jsp").forward(request, response);
        } else {
            response.sendRedirect("/customer/cart.jsp"); // 如果找不到购物车，重定向到购物车页面
        }
    }
}
