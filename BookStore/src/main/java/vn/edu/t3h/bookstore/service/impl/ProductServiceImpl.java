package vn.edu.t3h.bookstore.service.impl;

import vn.edu.t3h.bookstore.dao.ProductDao;
import vn.edu.t3h.bookstore.model.Product;
import vn.edu.t3h.bookstore.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {


    private ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }
    @Override
    public List<Product> getProductsByUser(String createdBy) {
        return productDao.getProductsByUser(createdBy);
    }

    @Override
    public void addProduct(Product product, int category_id) {
        productDao.insertProduct(product);
        productDao.insertProductCategory(product.getId(), category_id);
    }

    @Override
    public void deleteProduct(int id) {
        productDao.deleteProductCategory(id);
        productDao.deleteProduct(id);
    }
    @Override
    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }

    @Override
    public void updateProduct(Product product, int category_id) {
        productDao.updateProduct(product);
        productDao.updateProductCategory(product.getId(),category_id);
    }
}
