package model;

import java.util.Date;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String userRole;  // 改为 userRole
    private Date createdAt;

    public User() {
        super();
    }
    public User(int id, String username, String password, String email,String userRole ,Date createdAt) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
        this.createdAt = createdAt;
    }
    public User(String username, String password, String email ) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public String getUserRole() {
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    // Getters and Setters...
}
