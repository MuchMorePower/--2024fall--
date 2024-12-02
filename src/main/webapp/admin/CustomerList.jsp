<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>

<html>
<head>
    <title>客户列表</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }
        .container {
            width: 80%;
            max-width: 800px;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
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
            background-color: #009688;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        a {
            color: #009688;
            text-decoration: none;
            font-weight: bold;
            transition: color 0.3s;
        }
        a:hover {
            color: #00796b;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>客户列表</h1>
    <%
        // 从 request 中获取客户列表
        List<User> customers = (List<User>) request.getAttribute("customers");
    %>
    <table>
        <tr>
            <th>ID</th>
            <th>用户名</th>
            <th>操作</th>
        </tr>
        <%
            for (User customer : customers) {
        %>
        <tr>
            <td><%= customer.getId() %></td>
            <td><%= customer.getUsername() %></td>
            <td><a href="<%= request.getContextPath() %>/admin/CustomerDetail?userId=<%= customer.getId() %>">查看详情</a></td>

        </tr>
        <%
            }
        %>
    </table>
</div>
</body>
</html>
