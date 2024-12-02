<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 从请求参数获取总价
    double totalPrice = 0.0;
    String totalPriceParam = request.getParameter("totalPrice");
    if (totalPriceParam != null) {
        totalPrice = Double.parseDouble(totalPriceParam);
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>结算</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .container {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 90%;
            max-width: 400px;
            text-align: center;
        }
        h1 {
            color: #4CAF50;
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin: 15px 0 5px;
            color: #333;
            font-weight: bold;
        }
        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin: 5px 0 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[readonly] {
            background-color: #e9e9e9;
            cursor: not-allowed;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
            width: 100%;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>结算</h1>
    <form action="<%= request.getContextPath() %>/processPayment" method="post">
        <label for="cardNumber">信用卡号:</label>
        <input type="text" id="cardNumber" name="cardNumber" required placeholder="请输入信用卡号" />

        <label for="amount">支付金额:</label>
        <input type="text" id="amount" name="amount" required readonly value="<%= totalPrice %>" />

        <button type="submit">支付</button>
    </form>
</div>
</body>
</html>
