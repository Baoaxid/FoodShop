package controller;

import dao.CategoryDAO;
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
import model.Category;
import model.Product;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String c = request.getParameter("cid");
            int cid = 1;
            if(c != null) cid = Integer.parseInt(c);
            List<Product> fruits = new ArrayList<>(); 
            List<Product> p = new ArrayList<>();
            List<List<Product>> listProduct = new ArrayList<>();
            List<Product> vegetable = new ArrayList<>();
            
            ProductDAO productDAO = new ProductDAO();
            fruits = productDAO.getAll();
            
            
            vegetable = productDAO.getAllByCategoryId(1);

            CategoryDAO categoryDAO = new CategoryDAO();
            List<Category> categories = categoryDAO.getAll();
            for(int i = 0; i <= categories.size()-1; i++){
                p = productDAO.getAllByCategoryId(categories.get(i).getId());
                listProduct.add(p);
            }

            int count = 0;
            for (Category category : categories) {
                count++;
            }
            
            request.setAttribute("fruits", fruits);         
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("vegetable", vegetable);
            
            request.setAttribute("categories", categories);

            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
