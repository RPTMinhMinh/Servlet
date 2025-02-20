package vn.edu.t3h.bookstore.dao;

import vn.edu.t3h.bookstore.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAllProducts();

    List<Product> getProductsByUser(String createdBy);

    int insertProduct(Product product);

    int insertProductCategory(int product_id, int category_id);

    int updateProduct(Product product);

    int updateProductCategory(int product_id, int category_id);

    Product getProductById(int id);

    int deleteProduct(int id);

    int deleteProductCategory(int product_id);


}
