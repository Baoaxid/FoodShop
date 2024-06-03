package controller;

import dao.CategoryDAO;
import dao.ProductDAO;
import dao.ReviewDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Product;

@WebServlet(name = "ShopDetailServlet", urlPatterns = {"/shop-detail"})
public class ShopDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> fruits = new ArrayList<>();
        List<Product> featureFruits = new ArrayList<>();
        CategoryDAO categoryDAO = new CategoryDAO();
        ProductDAO productDAO = new ProductDAO();
        String id = request.getParameter("id");
        try {
            int idNum = Integer.parseInt(id);
            Product fd = productDAO.findById(idNum);
            ReviewDAO reviewDAO = new ReviewDAO();
            int rating = reviewDAO.getRatingByProductId(fd.getId());
            request.setAttribute("fruit", fd);
            List<Category> categories = categoryDAO.getAll();
            fruits = productDAO.getAllByCategoryId(fd.getId());
            featureFruits = productDAO.getAll();
            request.setAttribute("categories", categories);
            request.setAttribute("fruits", fruits);
            request.setAttribute("featureFruits", featureFruits);
            RequestDispatcher rd = request.getRequestDispatcher("shop-detail.jsp");
            rd.forward(request, response);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
