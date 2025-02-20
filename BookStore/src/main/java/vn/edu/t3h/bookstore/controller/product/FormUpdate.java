package vn.edu.t3h.bookstore.controller.product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.bookstore.dao.CategoryDao;
import vn.edu.t3h.bookstore.dao.ProductDao;
import vn.edu.t3h.bookstore.dao.impl.CategoryDaoImpl;
import vn.edu.t3h.bookstore.dao.impl.ProductDaoImpl;
import vn.edu.t3h.bookstore.model.Category;
import vn.edu.t3h.bookstore.model.Product;
import vn.edu.t3h.bookstore.service.CategoryService;
import vn.edu.t3h.bookstore.service.ProductService;
import vn.edu.t3h.bookstore.service.impl.CategoryServiceImpl;
import vn.edu.t3h.bookstore.service.impl.ProductServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "FormUpdate", value = "/cms/form-update")
public class FormUpdate extends HttpServlet {
    private ProductService productService;
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        super.init();
        ProductDao productDao = new ProductDaoImpl();
        productService = new ProductServiceImpl(productDao);
        CategoryDao categoryDao = new CategoryDaoImpl();
        categoryService = new CategoryServiceImpl(categoryDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.getProductById(id);
        List<Category> categories = categoryService.getAllCategories();

        req.setAttribute("categories", categories);
        req.setAttribute("product", product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("admin-productManager-updateUser.jsp");
        dispatcher.forward(req, resp);
    }
}
