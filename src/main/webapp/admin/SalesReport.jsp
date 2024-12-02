<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>销售报表</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 400px;
        }
        h1 {
            color: #333;
            font-size: 24px;
            margin-bottom: 20px;
        }
        p {
            font-size: 18px;
            color: #555;
        }
        .total-sales {
            font-size: 22px;
            font-weight: bold;
            color: #009688;
            margin-top: 10px;
            margin-bottom: 30px;
        }
        a {
            text-decoration: none;
            color: white;
            background-color: #009688;
            padding: 10px 20px;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
        a:hover {
            background-color: #00796b;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>销售报表</h1>
    <p>总销售额:</p>
    <p class="total-sales"><%= request.getAttribute("totalSales") %> 元</p> <!-- 输出 totalSales -->
    <a href="<%= request.getContextPath() %>/admin/home.jsp">返回主页</a> <!-- 返回主页链接 -->
</div>
</body>
</html>
