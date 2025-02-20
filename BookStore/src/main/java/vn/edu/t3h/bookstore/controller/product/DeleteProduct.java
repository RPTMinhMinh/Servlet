package vn.edu.t3h.bookstore.controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.bookstore.dao.ProductDao;
import vn.edu.t3h.bookstore.dao.impl.ProductDaoImpl;
import vn.edu.t3h.bookstore.service.ProductService;
import vn.edu.t3h.bookstore.service.impl.ProductServiceImpl;

import java.io.IOException;

@WebServlet(name = "DeleteProduct", value = "/cms/delete-product")
public class DeleteProduct extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        ProductDao productDao = new ProductDaoImpl();
        productService = new ProductServiceImpl(productDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        productService.deleteProduct(id);
        resp.sendRedirect("/cms/book_management");
    }
}
