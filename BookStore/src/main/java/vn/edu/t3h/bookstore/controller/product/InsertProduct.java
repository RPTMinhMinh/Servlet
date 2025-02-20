package vn.edu.t3h.bookstore.controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.bookstore.dao.ProductDao;
import vn.edu.t3h.bookstore.dao.impl.ProductDaoImpl;
import vn.edu.t3h.bookstore.model.Product;
import vn.edu.t3h.bookstore.service.ProductService;
import vn.edu.t3h.bookstore.service.impl.ProductServiceImpl;

import java.io.IOException;

@WebServlet(name = "InsertProduct", value = "/cms/insert-product")
public class InsertProduct extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        ProductDao productCategoryDao = new ProductDaoImpl();
        productService = new ProductServiceImpl(productCategoryDao);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        Integer pages = Integer.parseInt(req.getParameter("pages"));
        String publisher = req.getParameter("publisher");
        Integer yearPublishing = Integer.valueOf(req.getParameter("yearPublisher"));
        Integer categoryId = Integer.valueOf(req.getParameter("categoryId"));
        Double price = Double.valueOf(req.getParameter("price"));
        Integer discount = Integer.valueOf(req.getParameter("discount"));
        Integer quantity = Integer.valueOf(req.getParameter("quantity"));
        String description = req.getParameter("description");
        String image = req.getParameter("image");
        String createdBy = req.getParameter("createdBy");

        Product product = new Product();
        product.setName(name);
        product.setAuthor(author);
        product.setPages(pages);
        product.setPublisher(publisher);
        product.setYearPublishing(yearPublishing);
        product.setPrice(price);
        product.setDiscount(discount);
        product.setQuantity(quantity);
        product.setDescription(description);
        product.setImage(image);
        product.setCreateBy(createdBy);

        productService.addProduct(product, categoryId);
        resp.sendRedirect("/cms/book_management");
    }
}
