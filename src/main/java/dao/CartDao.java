package dao;

import model.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class CartDao extends BaseDao {
    public void save(Cart cart) {
        String sql = "INSERT INTO carts (user_id, created_at) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cart.getUserId());
            stmt.setTimestamp(2, new java.sql.Timestamp(cart.getCreatedAt().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int cartId) {
        String sql = "DELETE FROM carts WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cartId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 计算购物车的总价
    public double calculateTotalPrice(int cartId) {
        CartItemDao cartItemDao = new CartItemDao();
        return cartItemDao.calculateTotalPrice(cartId);
    }
    // 创建或获取用户的购物车
    public Cart findByUserId(int userId) {
        Cart cart = null;
        String sql = "SELECT * FROM carts WHERE user_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cart = new Cart();
                cart.setId(rs.getInt("id"));
                cart.setUserId(rs.getInt("user_id"));
                cart.setTotalPrice(rs.getDouble("total_price"));
                cart.setCreatedAt(rs.getDate("created_at"));
            } else {
                // 如果没有找到购物车，可以创建一个新的购物车
                cart = model.Cart.createCartForUser(userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }
    // 创建或获取用户的购物车
    public Cart find_ByUserId(int userId) {
        Cart cart = null;
        String sql = "SELECT * FROM carts WHERE user_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cart = new Cart();
                cart.setId(rs.getInt("id"));
                cart.setUserId(rs.getInt("user_id"));
                cart.setTotalPrice(rs.getDouble("total_price"));
                cart.setCreatedAt(rs.getDate("created_at"));
            } else {
                // 如果没有找到购物车，创建新的购物车并插入数据库
                cart = new Cart();
                cart.setUserId(userId);
                String insertSql = "INSERT INTO carts (user_id) VALUES (?)";

                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    insertStmt.setInt(1, userId);
                    insertStmt.executeUpdate();

                    ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        cart.setId(generatedKeys.getInt(1)); // 获取生成的购物车ID
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }


    public Cart get_ByUserId(int userId) {
        Cart cart = null;
        String sql = "SELECT * FROM carts WHERE user_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cart = new Cart();
                cart.setId(rs.getInt("id"));
                cart.setUserId(rs.getInt("user_id"));
                cart.setTotalPrice(rs.getDouble("total_price"));
                cart.setCreatedAt(rs.getDate("created_at"));
            } else {
                // 如果没有找到购物车，可以创建一个新的购物车
                cart = model.Cart.createCartForUser(userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }

    public void clearCart(int cartId) {
        String sql = "DELETE FROM carts WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cartId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 创建购物车
    public Cart createCart(int userId) {
        Cart cart = new Cart();
        String sql = "INSERT INTO carts (user_id) VALUES (?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            System.out.println("Connecting to the database...");

            // 设置用户ID
            stmt.setInt(1, userId);
            System.out.println("Executing SQL: " + sql + " with userId: " + userId);

            // 执行更新并获取受影响的行数
            int affectedRows = stmt.executeUpdate();
            System.out.println("Affected Rows: " + affectedRows);

            // 检查是否生成了主键
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int cartId = generatedKeys.getInt(1);
                cart.setId(cartId);
                cart.setUserId(userId);
                System.out.println("Generated Cart ID: " + cartId);
            } else {
                System.out.println("Failed to generate Cart ID.");
            }

        } catch (SQLException e) {
            System.err.println("Error executing SQL: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Returning created cart with ID: " + cart.getId() + " for userId: " + userId);
        return cart;
    }


    public Cart createNewCart(int userId) {
        Cart cart = new Cart();
        String sql = "INSERT INTO carts (user_id) VALUES (?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                cart.setId(generatedKeys.getInt(1)); // 获取生成的购物车ID
                cart.setUserId(userId);
                // 可以设置其他属性，例如总价等
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart; // 返回创建的购物车
    }
}
