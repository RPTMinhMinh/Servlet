package vn.edu.t3h.bookstore.service;

import vn.edu.t3h.bookstore.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getProductsByUser(String createdBy);
    void addProduct(Product product, int category_id);
    void deleteProduct(int id);
    Product getProductById(int id);
    void updateProduct(Product product, int category_id);

}
