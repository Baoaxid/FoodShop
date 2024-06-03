package controller;

import dao.UserDAO;
import dao.UserDAOImplement;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

@WebServlet(name = "RegisterAccountServlet", urlPatterns = {"/register"})
public class RegisterAccountServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String agreed = request.getParameter("agreed");
        boolean status = false;
        String errorMessage = "";
        String successMessage = "";
        if (agreed != null) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setEmail(email);
            newUser.setPasswordHash(password);
            UserDAO userDAO = new UserDAOImplement();
            status = userDAO.insertUser(username, firstName, lastName, email, password);
            if (status) {
                successMessage = "Register successfully !";
            } else {
                errorMessage = "Something went wrong ! Please again!";

            }

        } else {
            errorMessage = "Please agree with use term";

        }

        request.setAttribute("errorMessage", errorMessage);
        request.setAttribute("successMessage", successMessage);
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

}
