<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Order" %>

<html>
<head>
    <title>订单历史</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        h1 {
            color: #333;
        }
        a {
            text-decoration: none;
            color: #007bff;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h1>订单历史</h1>

<%
    List<Order> orders = (List<Order>) request.getAttribute("orders"); // 获取订单列表
    if (orders != null && !orders.isEmpty()) {
%>
<table>
    <thead>
    <tr>
        <th>订单 ID</th>
        <th>总金额</th>
        <th>状态</th>
        <th>创建时间</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Order order : orders) {
    %>
    <tr>
        <td><%= order.getId() %></td>
        <td><%= order.getTotal() %></td>
        <td><%= order.getStatus() %></td>
        <td><%= order.getCreatedAt() %></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<%
} else {
%>
<p>没有订单记录。</p>
<%
    }
%>

<a href="<%= request.getContextPath() %>/customer/home.jsp">返回主页</a> <!-- 使用表达式输出路径 -->
</body>
</html>
