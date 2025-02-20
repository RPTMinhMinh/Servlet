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
import vn.edu.t3h.bookstore.utils.Constants;

import java.io.IOException;

@WebServlet(name = "InsertUser", value = "/insert-user")
public class InsertUser extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        UserDao userDao = new UserDaoImpl();
        userService = new UserServiceImpl(userDao);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
//        String gender = req.getParameter("gender");
        String password = req.getParameter("password");
        String address = req.getParameter("address");
        String role = Constants.ROLE.CUSTOMER.name();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFullName(fullname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        user.setRole(role);

        userService.insertUser(user);
        resp.sendRedirect("/login");
    }
}
