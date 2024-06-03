package controller;

import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteProductServlet", urlPatterns = {"/admin/delete-product"})
public class DeleteProductServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        ProductDAO productDAO = new ProductDAO();
        String msg = "";
        if (productDAO.delete(id)) {
            msg = "Delete product successfully !";
        }else{
            msg = "Delete product unsucessfully !";
        }
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("products").forward(request, response);
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
