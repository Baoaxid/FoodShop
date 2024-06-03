package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LogoutController", urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie cUserName = new Cookie("cookuser", null);
        Cookie cPassword = new Cookie("cookpass", null);
        Cookie cRemember = new Cookie("cookrem", null);
        cUserName.setMaxAge(0);
        cPassword.setMaxAge(0);
        cRemember.setMaxAge(0);
        response.addCookie(cUserName);
        response.addCookie(cPassword);
        response.addCookie(cRemember);
        HttpSession session = request.getSession();
        session.invalidate();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

}
