package dao;

import model.BrowseHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrowseHistoryDao extends BaseDao {
    private ProductDao productDao = new ProductDao(); // 创建 ProductDao 的实例

    public void save(BrowseHistory history) {
        String sql = "INSERT INTO browse_history (user_id, product_id, browse_time) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, history.getUserId());
            stmt.setInt(2, history.getProductId());
            stmt.setTimestamp(3, new java.sql.Timestamp(history.getBrowseTime().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 获取用户的所有浏览记录
    public List<BrowseHistory> findByUserId(int userId) {
        List<BrowseHistory> histories = new ArrayList<>();
        String sql = "SELECT * FROM browse_history WHERE user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BrowseHistory history = new BrowseHistory();
                history.setUserId(rs.getInt("user_id"));
                history.setProductId(rs.getInt("product_id"));
                history.setBrowseTime(rs.getTimestamp("browse_time"));

                // 查询商品名并设置到浏览记录中
                String productName = productDao.findProductNameById(history.getProductId());
                history.setProductName(productName); // 假设 BrowseHistory 有 setProductName 方法

                histories.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return histories;
    }
}
