package servlet.admin;

import dao.ProductDao;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/productManagement")
public class ProductManagementServlet extends HttpServlet {
    private ProductDao productDao;

    @Override
    public void init() throws ServletException {
        productDao = new ProductDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productDao.findAll();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/admin/productManagement.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            // 添加单个商品
            String name = request.getParameter("name");
            String description = request.getParameter("description"); // 添加描述
            double price = Double.parseDouble(request.getParameter("price"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            Product product = new Product();
            product.setName(name);
            product.setDescription(description); // 设置描述
            product.setPrice(price);
            product.setStock(stock);
            productDao.save(product);
        } else if ("edit".equals(action)) {
            // 编辑商品
            int productId = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description"); // 获取描述
            double price = Double.parseDouble(request.getParameter("price"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            Product product = new Product();
            product.setId(productId);
            product.setName(name);
            product.setDescription(description); // 设置描述
            product.setPrice(price);
            product.setStock(stock);
            productDao.update(product); // 更新商品信息
        } else if ("delete".equals(action)) {
            // 删除商品
            int productId = Integer.parseInt(request.getParameter("id"));
            productDao.delete(productId);
        } else if ("increaseStock".equals(action)) {
            // 增加库存
            int productId = Integer.parseInt(request.getParameter("id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            productDao.increaseStock(productId, quantity);
        } else if ("decreaseStock".equals(action)) {
            // 减少库存
            int productId = Integer.parseInt(request.getParameter("id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            productDao.decreaseStock(productId, quantity);
        }
        response.sendRedirect("productManagement");
    }
}
