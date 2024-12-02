<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>
<%@ page import="model.Order" %>
<%@ page import="model.BrowseHistory" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.ProductDao" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>客户详情</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
        }
        .container {
            width: 100%;
            max-width: 800px;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        h1, h2 {
            color: #333;
        }
        p, td, th {
            color: #666;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>客户详情</h1>

    <h2>基本信息</h2>
    <p><strong>ID:</strong> <%= ((User) request.getAttribute("user")).getId() %></p>
    <p><strong>用户名:</strong> <%= ((User) request.getAttribute("user")).getUsername() %></p>
    <p><strong>邮箱:</strong> <%= ((User) request.getAttribute("user")).getEmail() %></p>
    <p><strong>角色:</strong> <%= ((User) request.getAttribute("user")).getUserRole() %></p>
    <p><strong>创建时间:</strong> <%= ((User) request.getAttribute("user")).getCreatedAt() %></p>

    <h2>订单记录</h2>
    <table>
        <tr>
            <th>订单ID</th>
            <th>总金额</th>
            <th>状态</th>
            <th>创建时间</th>
        </tr>
        <%
            List<Order> orders = (List<Order>) request.getAttribute("orders");
            for (Order order : orders) {
        %>
        <tr>
            <td><%= order.getId() %></td>
            <td>¥<%= String.format("%.2f", order.getTotal()) %></td>
            <td><%= order.getStatus() %></td>
            <td><%= order.getCreatedAt() %></td>
        </tr>
        <%
            }
        %>
    </table>

    <h2>浏览记录</h2>
    <table>
        <tr>
            <th>产品ID</th>
            <th>浏览时间</th>
            <th>产品名</th>
        </tr>
        <%
            List<BrowseHistory> histories = (List<BrowseHistory>) request.getAttribute("histories");
            ProductDao productDao = new ProductDao();
            for (BrowseHistory history : histories) {
                String productName = productDao.findProductNameById(history.getProductId());
        %>
        <tr>
            <td><%= history.getProductId() %></td>
            <td><%= history.getBrowseTime() %></td>
            <td><%= productName %></td>
        </tr>
        <%
            }
        %>
    </table>
</div>
</body>
</html>
