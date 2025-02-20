package vn.edu.t3h.bookstore.dao.impl;

import vn.edu.t3h.bookstore.dao.ProductDao;
import vn.edu.t3h.bookstore.model.Product;
import vn.edu.t3h.bookstore.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> getProductsByUser(String createdBy) {
        List<Product> products = new ArrayList<>();
        String sql = "select p.id, p.name, p.author, p.pages, p.publisher, c.name, p.yearPublishing,p.totalBuy from bookshopdb.product_category\n" +
                "join bookshopdb.category c on c.id = product_category.categoryId\n" +
                "join bookshopdb.product p on p.id = product_category.productId\n" +
                "where p.created_by = ?\n" +
                "ORDER BY p.id";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, createdBy);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setAuthor(resultSet.getString("author"));
                product.setPages(resultSet.getInt("pages"));
                product.setPublisher(resultSet.getString("publisher"));
                product.setCategoryName(resultSet.getString("c.name"));
                product.setYearPublishing(resultSet.getInt("yearPublishing"));
                product.setTotalBuy(resultSet.getInt("totalBuy"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "select p.id, p.name, p.author, p.pages, p.publisher, c.name, p.yearPublishing,p.totalBuy from bookshopdb.product_category\n" +
                "join bookshopdb.category c on c.id = product_category.categoryId\n" +
                "join bookshopdb.product p on p.id = product_category.productId ORDER BY p.id;";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setAuthor(resultSet.getString("author"));
                product.setPages(resultSet.getInt("pages"));
                product.setPublisher(resultSet.getString("publisher"));
                product.setCategoryName(resultSet.getString("c.name"));
                product.setYearPublishing(resultSet.getInt("yearPublishing"));
                product.setTotalBuy(resultSet.getInt("totalBuy"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public int insertProduct(Product product) {
        int row = 0;
        String sql = "insert into bookshopdb.product ( name, price, discount, quantity, author, pages, publisher, yearPublishing, description, imageName, createdAt, updatedAt, totalBuy, created_by)\n" +
                "value (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getDiscount());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setString(5, product.getAuthor());
            preparedStatement.setInt(6, product.getPages());
            preparedStatement.setString(7, product.getPublisher());
            preparedStatement.setInt(8, product.getYearPublishing());
            preparedStatement.setString(9, product.getDescription());
            preparedStatement.setString(10, product.getImage());
            preparedStatement.setString(11, LocalDateTime.now().toString());
            preparedStatement.setString(12, LocalDateTime.now().toString());
            preparedStatement.setInt(13, 0);
            preparedStatement.setString(14, product.getCreateBy());
            row = preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getInt(1)); // Gán giá trị id cho sản phẩm
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return row;
    }

    @Override
    public int insertProductCategory(int product_id, int category_id) {
        int row = 0;
        String sql = "insert into bookshopdb.product_category(productId, categoryId)\n" +
                "values(?,?)";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, product_id);
            preparedStatement.setInt(2, category_id);
            row = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return row;
    }

    @Override
    public int deleteProduct(int id) {
        int row = 0;
        String sql = "delete from bookshopdb.product where id = ?";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            row = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return row;
    }

    @Override
    public int deleteProductCategory(int product_id) {
        int row = 0;
        String sql = "delete from bookshopdb.product_category where productId = ?";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, product_id);
            row = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return row;
    }

    @Override
    public Product getProductById(int id) {
        Product product = null;
        String sql = "select p.id, p.name, p.author, p.pages, p.publisher, p.yearPublishing, c.id, p.price, p.discount, p.quantity,p.description,p.imageName\n" +
                "from bookshopdb.product_category\n" +
                "join bookshopdb.product p on p.id = product_category.productId\n" +
                "join bookshopdb.category c on c.id = product_category.categoryId\n" +
                "where p.id = ?";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("p.name"));
                product.setAuthor(resultSet.getString("author"));
                product.setPages(resultSet.getInt("pages"));
                product.setPublisher(resultSet.getString("publisher"));
                product.setYearPublishing(resultSet.getInt("yearPublishing"));
                product.setCategoryId(resultSet.getInt("c.id"));
                product.setPrice(resultSet.getDouble("price"));
                product.setDiscount(resultSet.getInt("discount"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setDescription(resultSet.getString("description"));
                product.setImage(resultSet.getString("imageName"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public int updateProduct(Product product) {
        int rows = 0;
        String sql = "update bookshopdb.product set \n" +
                "name = ?,\n" +
                "author = ?,\n" +
                "pages = ?,\n" +
                "publisher = ?,\n" +
                "yearPublishing = ?,\n" +
                "price = ?,\n" +
                "discount = ?,\n" +
                "quantity = ?,\n" +
                "description = ?,\n" +
                "imageName = ?,\n" +
                "updatedAt = ?\n" +
                "where id = ? ";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getAuthor());
            preparedStatement.setInt(3, product.getPages());
            preparedStatement.setString(4, product.getPublisher());
            preparedStatement.setInt(5, product.getYearPublishing());
            preparedStatement.setDouble(6, product.getPrice());
            preparedStatement.setInt(7, product.getDiscount());
            preparedStatement.setInt(8, product.getQuantity());
            preparedStatement.setString(9, product.getDescription());
            preparedStatement.setString(10, product.getImage());
            preparedStatement.setString(11, LocalDateTime.now().toString());
            preparedStatement.setInt(12, product.getId());
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }

    @Override
    public int updateProductCategory(int product_id, int category_id) {
        int rows = 0;
        String sql = "update bookshopdb.product_category set\n" +
                "categoryId = ?\n" +
                "where productId = ?\n";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, category_id);
            preparedStatement.setInt(2, product_id);
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }
}
