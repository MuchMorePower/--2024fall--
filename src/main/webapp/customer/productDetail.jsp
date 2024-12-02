<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Product" %>

<%
    Product product = (Product) request.getAttribute("product");
    if (product == null) {
        out.println("<h1>产品未找到</h1>");
        return; // 早期返回，避免后续代码执行
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><%= product.getName() %></title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 600px;
            text-align: center;
        }
        h1 {
            color: #4CAF50;
            margin-bottom: 20px;
        }
        p {
            color: #333;
            margin: 10px 0;
        }
        .success-message {
            color: green;
            margin: 20px 0;
        }
        label {
            display: block;
            margin-top: 15px;
            color: #555;
        }
        input[type="number"] {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 60px;
            margin-top: 5px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            margin-top: 20px;
        }
        button:hover {
            background-color: #45a049;
        }
        .back-button {
            background-color: #f44336;
            margin-top: 10px;
        }
        .back-button:hover {
            background-color: #e53935;
        }
    </style>
</head>
<body>
<div class="container">
    <h1><%= product.getName() %></h1>
    <p>价格: ¥<%= String.format("%.2f", product.getPrice()) %></p>
    <p>描述: <%= product.getDescription() %></p>
    <p>库存: <%= product.getStock() %></p>

    <% if (request.getAttribute("successMessage") != null) { %>
    <div class="success-message">
        <%= request.getAttribute("successMessage") %>
    </div>
    <% } %>

    <form action="<%= request.getContextPath() %>/customer/productDetail?id=<%= product.getId() %>" method="post">
        <label for="quantity">数量:</label>
        <input type="number" id="quantity" name="quantity" value="1" min="1" max="<%= product.getStock() %>" />
        <button type="submit">加入购物车</button>
    </form>

    <button class="back-button" onclick="window.history.back()">返回商品列表</button>
</div>
</body>
</html>
