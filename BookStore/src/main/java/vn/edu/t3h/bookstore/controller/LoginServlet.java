package vn.edu.t3h.bookstore.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.bookstore.dao.impl.UserDaoImpl;
import vn.edu.t3h.bookstore.service.UserService;
import vn.edu.t3h.bookstore.service.impl.UserServiceImpl;
import vn.edu.t3h.bookstore.utils.Constants;

import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserServiceImpl(new UserDaoImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().contains("/logout")){
            //delete session when logout
            req.getSession().removeAttribute(Constants.SESSION_ID_CURRENT_USER);
            resp.sendRedirect("/home");
        }else {
            String message = req.getParameter("message");
            req.setAttribute("message", message);
            req.getRequestDispatcher("signin.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String urlRedirect = userService.login(username, password, req);
        resp.sendRedirect(urlRedirect);
    }
}
