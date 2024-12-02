package model;

import java.util.Date;

public class BrowseHistory {
    private int id;        // 浏览记录的ID
    private int userId;    // 用户ID
    private int productId; // 产品ID
    private String productName; // 新增商品名属性
    private Date browseTime; // 浏览时间

    // Constructors
    public BrowseHistory() {}

    public BrowseHistory(int userId, int productId, Date browseTime) {
        this.userId = userId;
        this.productId = productId;
        this.browseTime = browseTime;
    }

    // Getters and Setters
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getBrowseTime() {
        return browseTime;
    }

    public void setBrowseTime(Date browseTime) {
        this.browseTime = browseTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
