<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Order" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
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
        .order-details {
            width: 60%;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            text-align: left;
            margin-bottom: 20px;
        }
        .order-details p {
            font-size: 16px;
            line-height: 1.6;
            color: #555;
        }
        .order-action {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .cancel-button {
            padding: 8px 16px;
            color: #fff;
            background-color: #f44336;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
        }
        .cancel-button:hover {
            background-color: #d32f2f;
        }
        .back-link {
            text-decoration: none;
            color: #4CAF50;
            font-weight: bold;
        }
        .back-link:hover {
            color: #388E3C;
        }
    </style>
</head>
<body>

<%
    // 从 request 中获取订单对象
    Order order = (Order) request.getAttribute("order");
%>

<div class="order-details">
    <h2>订单详情 (ID: <%= order.getId() %>)</h2>
    <p><strong>用户ID:</strong> <%= order.getUserId() %></p>
    <p><strong>总金额:</strong> ￥<%= order.getTotal() %></p>
    <p><strong>状态:</strong> <%= order.getStatus() %></p>
    <p><strong>创建时间:</strong> <%= order.getCreatedAt() %></p>
</div>

<div class="order-action">
    <form action="<%= request.getContextPath() %>/admin/OrderDetail" method="post">
        <input type="hidden" name="orderId" value="<%= order.getId() %>" />
        <input type="submit" class="cancel-button" value="Cancel Order" onclick="return confirm('Are you sure you want to cancel this order?');" />
    </form>

    <a href="<%= request.getContextPath() %>/admin/OrderList" class="back-link">返回订单列表</a>
</div>

</body>
</html>
