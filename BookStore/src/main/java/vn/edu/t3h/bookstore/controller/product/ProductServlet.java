package vn.edu.t3h.bookstore.controller.product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.bookstore.dao.ProductDao;
import vn.edu.t3h.bookstore.dao.UserDao;
import vn.edu.t3h.bookstore.dao.impl.ProductDaoImpl;
import vn.edu.t3h.bookstore.dao.impl.UserDaoImpl;
import vn.edu.t3h.bookstore.model.Product;
import vn.edu.t3h.bookstore.model.User;
import vn.edu.t3h.bookstore.service.ProductService;
import vn.edu.t3h.bookstore.service.UserService;
import vn.edu.t3h.bookstore.service.impl.ProductServiceImpl;
import vn.edu.t3h.bookstore.service.impl.UserServiceImpl;
import vn.edu.t3h.bookstore.utils.Constants;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/cms/book_management")
public class ProductServlet extends HttpServlet {
    private ProductService productService;
    private UserService userService;
    @Override
    public void init() throws ServletException {
        super.init();
        ProductDao productDao = new ProductDaoImpl();
        productService = new ProductServiceImpl(productDao);
        UserDao userDao = new UserDaoImpl();
        userService = new UserServiceImpl(userDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String createdBy = req.getParameter("createdBy");
        List<Product> products;

        User user = (User) req.getSession().getAttribute(Constants.SESSION_ID_CURRENT_USER);
        if(user != null && user.getRole().equals("ADMIN")){
            products = productService.getAllProducts();
        } else if (user != null && user.getRole().equals("EMPLOYEE")) {
            products = productService.getProductsByUser(user.getUsername());
        } else {
            return;
        }
        req.setAttribute("user", user);
        req.setAttribute("products", products);
        RequestDispatcher dispatcher = req.getRequestDispatcher("admin-productManager.jsp");
        dispatcher.forward(req, resp);
    }
}
