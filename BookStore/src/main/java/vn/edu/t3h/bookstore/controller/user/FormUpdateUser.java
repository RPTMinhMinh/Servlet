package vn.edu.t3h.bookstore.controller.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.bookstore.dao.UserDao;
import vn.edu.t3h.bookstore.dao.impl.UserDaoImpl;
import vn.edu.t3h.bookstore.model.User;
import vn.edu.t3h.bookstore.service.UserService;
import vn.edu.t3h.bookstore.service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(name = "FormUpdateUser", value = "/cms/form-update-user")
public class FormUpdateUser extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        UserDao userDao = new UserDaoImpl();
        userService = new UserServiceImpl(userDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = userService.getUserById(id);
        req.setAttribute("user", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("admin-productManager-updateUser.jsp");
        dispatcher.forward(req, resp);
    }
}
