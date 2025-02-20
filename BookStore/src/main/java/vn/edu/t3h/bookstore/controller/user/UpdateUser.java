package vn.edu.t3h.bookstore.controller.user;

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

@WebServlet(name = "UpdateUser", value = "/cms/update-user")
public class UpdateUser extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        UserDao dao = new UserDaoImpl();
        userService = new UserServiceImpl(dao);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String role = req.getParameter("role");

        User user = new User();
        user.setId(id);
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setFullName(fullname);
        user.setPhone(phone);
        user.setAddress(address);
        user.setRole(role);

        userService.updateUser(user);
        resp.sendRedirect("/cms/user-management");
    }
}
