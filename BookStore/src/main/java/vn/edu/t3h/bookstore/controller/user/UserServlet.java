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
import vn.edu.t3h.bookstore.utils.Constants;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/cms/user-management")
public class UserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        UserDao dao = new UserDaoImpl();
        userService = new UserServiceImpl(dao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(Constants.SESSION_ID_CURRENT_USER);
        if (user == null || !user.getRole().equals("ADMIN")) {
            resp.sendRedirect(req.getContextPath() + "/login?message=" +Constants.PERMISSION_DENIED);
            return;
        }
        List<User> users = userService.getAllUsers();
        req.setAttribute("users", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("admin-userManager.jsp");
        dispatcher.forward(req, resp);
    }
}
