package dao;

import model.SalesReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalesReportDao extends BaseDao {
    // 获取所有销售报表数据
    public List<SalesReport> getAllSalesReports() {
        List<SalesReport> reportList = new ArrayList<>();
        String sql = "SELECT p.id AS product_id, p.name AS product_name, SUM(oi.quantity) AS total_quantity, " +
                "SUM(oi.quantity * p.price) AS total_sales, CURRENT_DATE() AS report_date " +
                "FROM order_items oi " +
                "JOIN products p ON oi.product_id = p.id " +
                "GROUP BY p.id, p.name";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                SalesReport report = new SalesReport();
                report.setProductId(rs.getInt("product_id"));
                report.setProductName(rs.getString("product_name"));
                report.setTotalQuantity(rs.getInt("total_quantity"));
                report.setTotalSales(rs.getDouble("total_sales"));
                report.setReportDate(new Date(rs.getDate("report_date").getTime()));
                reportList.add(report);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportList;
    }

    // 根据时间范围获取销售报表
    public List<SalesReport> getSalesReportByDateRange(Date startDate, Date endDate) {
        List<SalesReport> reportList = new ArrayList<>();
        String sql = "SELECT p.id AS product_id, p.name AS product_name, SUM(oi.quantity) AS total_quantity, " +
                "SUM(oi.quantity * p.price) AS total_sales, CURRENT_DATE() AS report_date " +
                "FROM order_items oi " +
                "JOIN products p ON oi.product_id = p.id " +
                "JOIN orders o ON oi.order_id = o.id " +
                "WHERE o.created_at BETWEEN ? AND ? " +
                "GROUP BY p.id, p.name";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, new java.sql.Date(startDate.getTime()));
            stmt.setDate(2, new java.sql.Date(endDate.getTime()));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                SalesReport report = new SalesReport();
                report.setProductId(rs.getInt("product_id"));
                report.setProductName(rs.getString("product_name"));
                report.setTotalQuantity(rs.getInt("total_quantity"));
                report.setTotalSales(rs.getDouble("total_sales"));
                report.setReportDate(new Date(rs.getDate("report_date").getTime()));
                reportList.add(report);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportList;
    }
    public double getTotalSales() {
        double totalSales = 0.0;
        String sql = "SELECT SUM(total) AS totalSales FROM orders";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                totalSales = rs.getDouble("totalSales");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalSales;
    }

}
