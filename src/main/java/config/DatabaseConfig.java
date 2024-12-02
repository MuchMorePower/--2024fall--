package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConfig {
    private static ComboPooledDataSource dataSource;

    static {
        try {
            dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver"); // MySQL 8 驱动
            dataSource.setJdbcUrl("jdbc:mysql://8.140.235.154:3306/online_shopping?useSSL=false&serverTimezone=UTC");
            dataSource.setUser("root");
            dataSource.setPassword("Dy20040802");

            // C3P0 连接池的配置
            dataSource.setMinPoolSize(5);
            dataSource.setAcquireIncrement(5);
            dataSource.setMaxPoolSize(20);
            dataSource.setMaxIdleTime(300);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
            throw new RuntimeException("Error initializing DataSource", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(true); // 确保自动提交为开启状态
        return connection;
    }

}
