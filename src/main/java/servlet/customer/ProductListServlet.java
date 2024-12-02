package servlet.customer;

import dao.ProductDao;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/customer/productList")
public class ProductListServlet extends HttpServlet {
    private ProductDao productDao;

    @Override
    public void init() throws ServletException {
        productDao = new ProductDao();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productDao.findAllProducts();
        // 打印产品数量
        System.out.println("Number of products retrieved: " + (products != null ? products.size() : 0));
        request.setAttribute("products", products);
        request.getRequestDispatcher("/customer/productList.jsp").forward(request, response);
    }
}
