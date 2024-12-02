<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Order" %> <!-- 确保导入订单模型 -->

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>订单列表</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        h2 {
            color: #333;
            margin-top: 20px;
        }
        table {
            border-collapse: collapse;
            width: 80%;
            margin-top: 20px;
            background-color: #ffffff;
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
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        a {
            color: #4CAF50;
            text-decoration: none;
            padding: 6px 12px;
            border: 1px solid #4CAF50;
            border-radius: 4px;
            transition: background-color 0.3s ease, color 0.3s ease;
        }
        a:hover {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
<h2>订单列表</h2>
<table>
    <tr>
        <th>订单ID</th>
        <th>用户ID</th>
        <th>总金额</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    <%
        // 获取订单列表
        List<Order> orders = (List<Order>) request.getAttribute("orders");
        if (orders != null && !orders.isEmpty()) {
            for (Order order : orders) {
    %>
    <tr>
        <td><%= order.getId() %></td>
        <td><%= order.getUserId() %></td>
        <td>¥<%= String.format("%.2f", order.getTotal()) %></td> <!-- 格式化金额显示 -->
        <td><%= order.getStatus() %></td>
        <td>
            <a href="<%= request.getContextPath() %>/admin/OrderDetail?orderId=<%= order.getId() %>">查看详情</a>
        </td>
    </tr>
    <%
        } // 结束 for 循环
    } else {
    %>
    <tr>
        <td colspan="5">没有订单记录。</td>
    </tr>
    <%
        } // 结束 if 语句
    %>
</table>
</body>
</html>
