package controller;

import dao.CategoryDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Product;

@WebServlet(name = "ShopServlet", urlPatterns = {"/shop"})
public class ShopServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cid = request.getParameter("cid");
        ProductDAO productDAO = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categories = categoryDAO.getAll();
        List<Product> fruits = new ArrayList();
        if (cid != null) {
            int cidNum = Integer.parseInt(cid);
            fruits = productDAO.getAllByCategoryId(cidNum);
            request.setAttribute("cid", cid);
        } else {
            fruits = productDAO.getAll();
        }
        System.out.println("Thai Tieu Bao");
        System.out.println(categories);
        for (Product fruit : fruits) {
            System.out.println(fruit);
        }
        
        request.setAttribute("fruits",fruits);
        request.setAttribute("categories", categories);
        request.setAttribute("totalPages", 1);
        request.getRequestDispatcher("shop.jsp").forward(request, response);;

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
