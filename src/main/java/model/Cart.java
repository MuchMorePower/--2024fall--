package model;

import java.util.Date;
import dao.CartItemDao;


public class Cart {
    private int id; // 购物车ID
    private int userId; // 用户ID
    private double totalPrice; // 总价
    private Date createdAt; // 创建时间

    public Cart() {
        this.createdAt = new Date(); // 初始化为当前时间
    }
    // 静态方法用于创建购物车
    public static Cart createCartForUser(int userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setTotalPrice(0.0); // 初始化总价为0
        cart.setCreatedAt(new Date()); // 设置创建时间为当前时间
        return cart;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // 添加CartItem到购物车
    public void addItem(CartItem item) {
        CartItemDao cartItemDao = new CartItemDao();
        cartItemDao.addCartItem(item);
        updateTotalPrice();
    }

    // 从购物车中删除CartItem
    public void removeItem(int productId) {
        CartItemDao cartItemDao = new CartItemDao();
        cartItemDao.removeCartItem(productId);
        updateTotalPrice();
    }

    // 更新CartItem数量
    public void updateItemQuantity(int productId, int quantity) {
        CartItemDao cartItemDao = new CartItemDao();
        cartItemDao.updateCartItem(productId, quantity);
        updateTotalPrice();
    }

    // 更新总价
    private void updateTotalPrice() {
        CartItemDao cartItemDao = new CartItemDao();
        this.totalPrice = cartItemDao.calculateTotalPrice(this.id);
    }
}
