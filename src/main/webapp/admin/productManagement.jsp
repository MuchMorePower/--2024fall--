<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Product Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f9f9f9;
        }

        h1 {
            color: #333;
        }

        form {
            margin-bottom: 20px;
        }

        input[type="text"], input[type="number"], textarea {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: #28a745;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: white;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .action-buttons {
            display: flex;
            gap: 5px;
        }

        .action-buttons form {
            display: inline;
            flex: 1;
        }
    </style>
</head>
<body>
<h1>Product Management</h1>

<h2>Add New Product</h2>
<form method="post" action="productManagement">
    <input type="text" name="name" placeholder="Product Name" required>
    <textarea name="description" placeholder="Product Description" required></textarea>
    <input type="number" name="price" placeholder="Product Price" step="0.01" required>
    <input type="number" name="stock" placeholder="Stock" required>
    <input type="hidden" name="action" value="add">
    <input type="submit" value="Add Product">
</form>

<h2>Existing Products</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Stock</th>
        <th>Action</th>
    </tr>
    <%
        List<Product> products = (List<Product>) request.getAttribute("products");
        if (products != null) {
            for (Product product : products) {
    %>
    <tr>
        <td><%= product.getId() %></td>
        <td><%= product.getName() %></td>
        <td><%= product.getDescription() %></td>
        <td><%= product.getPrice() %></td>
        <td><%= product.getStock() %></td>
        <td>
            <div class="action-buttons">
                <form method="post" action="productManagement">
                    <input type="hidden" name="id" value="<%= product.getId() %>">
                    <input type="hidden" name="action" value="delete">
                    <input type="submit" value="Delete">
                </form>
                <form method="post" action="productManagement">
                    <input type="hidden" name="id" value="<%= product.getId() %>">
                    <input type="text" name="name" value="<%= product.getName() %>" required>
                    <textarea name="description" required><%= product.getDescription() %></textarea>
                    <input type="number" name="price" value="<%= product.getPrice() %>" step="0.01" required>
                    <input type="number" name="stock" value="<%= product.getStock() %>" required>
                    <input type="hidden" name="action" value="edit">
                    <input type="submit" value="Update">
                </form>
                <form method="post" action="productManagement">
                    <input type="hidden" name="id" value="<%= product.getId() %>">
                    <input type="number" name="quantity" placeholder="Quantity" required>
                    <input type="hidden" name="action" value="increaseStock">
                    <input type="submit" value="Increase Stock">
                </form>
                <form method="post" action="productManagement">
                    <input type="hidden" name="id" value="<%= product.getId() %>">
                    <input type="number" name="quantity" placeholder="Quantity" required>
                    <input type="hidden" name="action" value="decreaseStock">
                    <input type="submit" value="Decrease Stock">
                </form>
            </div>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="6">No products available.</td>
    </tr>
    <%
        }
    %>
</table>
<a href="<%= request.getContextPath() %>/admin/home.jsp">Back to Home</a>
</body>
</html>
