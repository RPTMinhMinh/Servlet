package vn.edu.t3h.bookstore.controller.product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.bookstore.dao.CategoryDao;
import vn.edu.t3h.bookstore.dao.impl.CategoryDaoImpl;
import vn.edu.t3h.bookstore.model.Category;
import vn.edu.t3h.bookstore.service.CategoryService;
import vn.edu.t3h.bookstore.service.impl.CategoryServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "FormInsert", value = "/cms/form_insert")
public class FormInsert extends HttpServlet {
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        super.init();
        CategoryDao categoryDao = new CategoryDaoImpl();
        categoryService = new CategoryServiceImpl(categoryDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryService.getAllCategories();
        req.setAttribute("categories", categories);
        RequestDispatcher dispatcher = req.getRequestDispatcher("admin-productManager-addProduct.jsp");
        dispatcher.forward(req, resp);
    }
}
