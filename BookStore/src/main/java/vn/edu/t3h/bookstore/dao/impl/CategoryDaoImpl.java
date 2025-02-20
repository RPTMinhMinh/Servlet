package vn.edu.t3h.bookstore.dao.impl;

import vn.edu.t3h.bookstore.dao.CategoryDao;
import vn.edu.t3h.bookstore.model.Category;
import vn.edu.t3h.bookstore.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<>();
        String sql = "select category.id, category.name from bookshopdb.category";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }
}
