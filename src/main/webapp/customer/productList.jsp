<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            min-height: 100vh;
            margin: 0;
        }
        h2 {
            color: #4CAF50;
            margin-bottom: 20px;
        }
        table {
            width: 80%;
            border-collapse: collapse;
            margin-bottom: 20px;
            background-color: #fff;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        td a {
            color: #333;
            text-decoration: none;
            font-weight: bold;
        }
        td a:hover {
            color: #4CAF50;
        }
        .no-products {
            font-size: 16px;
            color: #666;
            text-align: center;
            padding: 20px;
        }
        .logout-link {
            text-decoration: none;
            color: #4CAF50;
            font-weight: bold;
            margin-top: 20px;
        }
        .logout-link:hover {
            color: #388E3C;
        }
    </style>
</head>
<body>
<h2>产品列表</h2>

<%
    // 获取产品列表
    List<Product> products = (List<Product>) request.getAttribute("products");
%>

<table>
    <tr>
        <th>名称</th>
        <th>价格</th>
    </tr>
    <%
        // 检查产品列表是否为空
        if (products != null && !products.isEmpty()) {
            for (Product product : products) {
    %>
    <tr>
        <td><a href="productDetail?id=<%= product.getId() %>"><%= product.getName() %></a></td>
        <td>￥<%= product.getPrice() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="2" class="no-products">暂无产品</td>
    </tr>
    <%
        }
    %>
</table>

<a href="<%= request.getContextPath() %>/logout" class="logout-link">登出</a>
</body>
</html>
