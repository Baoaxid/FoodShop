/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProductDAO;
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
import model.Product;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "SearchProductServlet", urlPatterns = {"/admin/search"})
public class SearchProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String search = request.getParameter("search");
        List<Product> fruits = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        fruits = productDAO.getAllByName(search);
        request.setAttribute("fruits", fruits);
        RequestDispatcher rd = request.getRequestDispatcher("products.jsp");
        rd.forward(request, response);
        
    }
}
