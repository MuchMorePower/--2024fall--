package servlet.admin;

import dao.SalesReportDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/SalesReport")
public class AdminSalesReportServlet extends HttpServlet {
    private SalesReportDao salesReportDao = new SalesReportDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double totalSales = salesReportDao.getTotalSales();
        request.setAttribute("totalSales", totalSales);
        request.getRequestDispatcher("/admin/SalesReport.jsp").forward(request, response);
    }
}
