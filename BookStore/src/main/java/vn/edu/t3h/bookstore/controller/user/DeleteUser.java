package vn.edu.t3h.bookstore.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.bookstore.dao.UserDao;
import vn.edu.t3h.bookstore.dao.impl.UserDaoImpl;
import vn.edu.t3h.bookstore.service.UserService;
import vn.edu.t3h.bookstore.service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(name = "DeleteUser", value = "/cms/delete-user")
public class DeleteUser extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        UserDao dao = new UserDaoImpl();
        userService = new UserServiceImpl(dao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        userService.deleteUser(id);
        resp.sendRedirect("/cms/user-management");
    }
}
