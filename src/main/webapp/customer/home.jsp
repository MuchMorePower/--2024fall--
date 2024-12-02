<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        h2 {
            color: #333;
        }
        .container {
            width: 100%;
            max-width: 600px;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        p {
            margin: 15px 0;
        }
        a {
            display: inline-block;
            text-decoration: none;
            color: #4CAF50;
            background-color: #e7f3fe;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s, color 0.3s;
        }
        a:hover {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Welcome to the Shopping Site</h2>
    <p>
        <a href="<%= request.getContextPath() %>/customer/productList">View Product List</a>
    </p>
    <p>
        <a href="<%= request.getContextPath() %>/customer/orderHistory">View Order History</a>
    </p>
    <p>
        <a href="<%= request.getContextPath() %>/customer/cart">View Cart</a>
    </p>

</div>
</body>
</html>
