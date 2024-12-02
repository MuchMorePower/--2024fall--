<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>客户账号管理</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .AccountManagement {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }
        h1 {
            color: #4CAF50;
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin: 10px 0 5px;
            text-align: left;
        }
        input[type="text"], input[type="email"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
        }
        button:hover {
            background-color: #45a049;
        }
        .errorMessage {
            color: #f44336;
        }
        .successMessage {
            color: #4CAF50;
        }
    </style>
</head>
<body>
<div class="AccountManagement">
    <h1>Account Management</h1>
    <%
        User currentUser = (User) request.getAttribute("currentUser");
        if (currentUser == null) {
            // 处理未找到用户的情况，例如重定向到登录页面
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
    %>
    <form action="<%= request.getContextPath() + "/customer/customerManagement"%>" method="post">
        <input type="hidden" name="currentUsername" value="<%= currentUser.getUsername() %>"> <!-- 当前用户名 -->
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="<%= currentUser.getUsername() %>" required>
        <label for="password">Password:</label>
        <input type="text" id="password" name="password" required>
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" value="<%= currentUser.getEmail() %>" required>
        <button type="submit">Update</button>
    </form>

    <% if(request.getAttribute("error") != null) { %>
    <p class="errorMessage"><%=request.getAttribute("error")%></p>
    <% } %>
    <% if (request.getAttribute("message") != null) { %>
    <p class="successMessage"><%=request.getAttribute("message")%></p>
    <% } %>
</div>
</body>
</html>
