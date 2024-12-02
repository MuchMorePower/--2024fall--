<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.CartItem" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            min-height: 100vh;
            margin: 0;
        }
        h1 {
            color: #4CAF50;
        }
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px 0;
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
        td form {
            display: inline;
        }
        td input[type="number"] {
            width: 60px;
            text-align: center;
        }
        td button {
            background-color: #f44336;
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        td button:hover {
            background-color: #e53935;
        }
        .checkout-container {
            margin-top: 20px;
            text-align: right;
            width: 80%;
        }
        .checkout-container h3 {
            display: inline-block;
            margin-right: 20px;
            font-size: 18px;
            font-weight: bold;
        }
        .checkout-container button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .checkout-container button:hover {
            background-color: #45a049;
        }
        .back-link {
            margin-top: 20px;
        }
        .back-link a {
            text-decoration: none;
            color: #4CAF50;
            font-weight: bold;
        }
    </style>
</head>
<body>
<h1>购物车</h1>

<% List<CartItem> cartItems = (List<CartItem>) request.getAttribute("cartItems"); %>
<% double totalPrice = 0; %>

<% if (cartItems != null && !cartItems.isEmpty()) { %>
<table>
    <tr>
        <th>商品ID</th>
        <th>商品名</th>
        <th>数量</th>
        <th>单价</th>
        <th>小计</th>
        <th>操作</th>
    </tr>
    <% for (CartItem item : cartItems) {
        double itemTotal = item.getPrice() * item.getQuantity();
        totalPrice += itemTotal;
    %>
    <tr>
        <td><%= item.getProductId() %></td>
        <td><%= item.getProductName() %></td>
        <td>
            <form action="<%= request.getContextPath() %>/customer/cart" method="post">
                <input type="hidden" name="action" value="update" />
                <input type="hidden" name="productId" value="<%= item.getProductId() %>" />
                <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="1" />
                <button type="submit">修改</button>
            </form>
        </td>
        <td>￥<%= item.getPrice() %></td>
        <td>￥<%= itemTotal %></td>
        <td>
            <form action="<%= request.getContextPath() %>/customer/cart" method="post">
                <input type="hidden" name="action" value="remove" />
                <input type="hidden" name="productId" value="<%= item.getProductId() %>" />
                <button type="submit">删除</button>
            </form>
        </td>
    </tr>
    <% } %>
</table>

<div class="checkout-container">
    <h3>总价: ￥<%= totalPrice %></h3>
    <form action="<%= request.getContextPath() %>/customer/checkout.jsp" method="post">
        <input type="hidden" name="action" value="checkout" />
        <input type="hidden" name="totalPrice" value="<%= totalPrice %>" />
        <button type="submit">结算</button>
    </form>
</div>

<% } else { %>
<p>购物车为空</p>
<% } %>

<div class="back-link">
    <a href="<%= request.getContextPath() %>/customer/productList">继续购物</a>
</div>
</body>
</html>
