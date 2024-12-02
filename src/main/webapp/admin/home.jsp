<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Admin Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            color: #333;
        }
        .container {
            width: 80%;
            max-width: 600px;
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        nav {
            margin-top: 20px;
        }
        nav h2 {
            color: #666;
            text-align: center;
        }
        nav ul {
            list-style: none;
            padding: 0;
            margin: 0;
            text-align: center;
        }
        nav ul li {
            margin: 10px 0;
        }
        nav ul li a {
            color: #0073e6;
            text-decoration: none;
            font-weight: bold;
            font-size: 16px;
            transition: color 0.3s;
        }
        nav ul li a:hover {
            color: #005bb5;
        }
        .tasks {
            text-align: center;
            margin-top: 30px;
            color: #555;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Welcome to the Admin Dashboard</h1>
    <nav>
        <h2>Management Options</h2>
        <ul>
            <li><a href="<%= request.getContextPath() %>/admin/productManagement">Product Management</a></li>
            <li><a href="<%= request.getContextPath() %>/admin/OrderList">Order Management</a></li>
            <li><a href="<%= request.getContextPath() %>/admin/SalesReport">Sales Statistics Report</a></li>
            <li><a href="<%= request.getContextPath() %>/admin/CustomerList">Customer Management</a></li>
            <li><a href="<%= request.getContextPath() %>/login">Logout</a></li>
        </ul>
    </nav>
    <div class="tasks">
        <h2>Admin Tasks</h2>
        <p>Use the links above to manage products, orders, customers, and view sales reports.</p>
    </div>
</div>
</body>
</html>
