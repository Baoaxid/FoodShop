/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAOImplement;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            HttpSession session = request.getSession();
            User user = authenticatedUser(username, password);
            if (user != null) {
                String remember = request.getParameter("rem");
                if (remember != null) {
                    Cookie cRemember = new Cookie("cookrem", remember.trim());
                    Cookie cUsername = new Cookie("cookuser", user.getUsername());
                    Cookie cPassword = new Cookie("cookpass", password);
                    cUsername.setMaxAge(60 * 60 * 24 * 15);// 15 days
                    cPassword.setMaxAge(60 * 60 * 24 * 15);
                    cRemember.setMaxAge(60 * 60 * 24 * 15);
                    response.addCookie(cUsername);
                    response.addCookie(cPassword);
                    response.addCookie(cRemember);
                }
                //sessionScope.currentUser
                session.setAttribute("userObj", user);
                if (user.getRole() != 0) {
                    response.sendRedirect("admin/index.jsp");
                } else {
                    response.sendRedirect("home");
                }
            } else {
                request.setAttribute("errorLogin", "Wrong username or password!! Please try again!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private User authenticatedUser(String username, String password) throws Exception {
        UserDAOImplement udi = new UserDAOImplement();
        User user = udi.getUserByUsername(username);
        try {
            if (user != null && user.getPasswordHash().equals(hashPassword(password))) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String hashPassword(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(password.getBytes());

        StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
