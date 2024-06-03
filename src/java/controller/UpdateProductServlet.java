package controller;

import dao.CategoryDAO;
import dao.ProductDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Category;
import model.Product;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@MultipartConfig
@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/admin/update-product"})
public class UpdateProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        ProductDAO productDAO = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categories = categoryDAO.getAll();
        Product product = productDAO.findById(Integer.parseInt(id));
        request.setAttribute("fruit", product);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("update-product.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("----------------------------------------------------");
        try {

            int id = 0;
            String name = null;
            String description = null;
            double price = 0;
            int categoryId = 0;
            String imagePath = null;
            double weight = 0.0;
            String origin = null;
            String quality = null;
            int quantity = 0;
            boolean test = false;

            id = Integer.parseInt(request.getParameter("id"));
            name = request.getParameter("name");
            categoryId = Integer.parseInt(request.getParameter("category"));
            price = Double.parseDouble(request.getParameter("price"));
            description = request.getParameter("description");
            weight = Double.parseDouble(request.getParameter("weight"));
            quantity = Integer.parseInt(request.getParameter("quantity"));
            origin = request.getParameter("origin");
            quality = request.getParameter("quality");
            test = Boolean.parseBoolean(request.getParameter("test"));
            //path: img\licensed-image.jpg
            imagePath = "img\\" + request.getParameter("image");

            // Lưu sản phẩm vào DB
            Product product = new Product(name, imagePath, categoryId, price, description, weight, origin, quality, test, quantity);
            product.setId(id);
            System.out.println("Updated Product: " + product);

            ProductDAO productDAO = new ProductDAO();
            if(imagePath.equals("img\\")){
                Product p = productDAO.findById(id);
                imagePath = p.getImage();
                product.setImage(imagePath);
            }
            boolean status = productDAO.update(product);
            if (status) {
                String msg = "Update product successfully !";
                request.setAttribute("msg", msg);
                System.out.println("Success");
                doGet(request, response);
            } else {
                request.setAttribute("errorMessage", "Sai du lieu");
                System.out.println("Fail");
                doGet(request, response);
            }
            doGet(request, response);
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Lỗi trong quá trình upload file: " + ex.getMessage());
            ex.printStackTrace();
//            response.sendRedirect("update-product?id=" + idProduct);
            doGet(request, response);
        }

    }

}
