package vn.edu.t3h.bookstore.dao.impl;

import vn.edu.t3h.bookstore.dao.UserDao;
import vn.edu.t3h.bookstore.model.User;
import vn.edu.t3h.bookstore.utils.DatabaseConnection;
import vn.edu.t3h.bookstore.utils.PasswordUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User findUserByUserNameAndPassword(String username, String password) {
        User user = null;
        String sql = "select * from bookshopdb.user\n" +
                "where user.username = ?\n" +
                "and user.password = ?";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String fullname = resultSet.getString("fullname");
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phoneNumber");
                    String address = resultSet.getString("address");
                    String role = resultSet.getString("role");
                    user = new User(id, username, password, fullname, email, phone, address, role);
                }


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "select id, username, fullname, email, phoneNumber, address, role\n" +
                "from bookshopdb.user";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setFullName(resultSet.getString("fullname"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phoneNumber"));
                user.setAddress(resultSet.getString("address"));
                user.setRole(resultSet.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public int insertUser(User user) {
        int rows = 0;
        String sql = "insert into bookshopdb.user\n" +
                "    (username, password, fullname, email, phoneNumber, address, role)\n" +
                "value\n" +
                "    (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setString(7, user.getRole());
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }

    @Override
    public User getUserById(int id) {
        User user = null;
        String sql = "select id, username, password, fullname, email, phoneNumber, address, role\n" +
                "from bookshopdb.user\n" +
                "where id = ?";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));

                String password = resultSet.getString("password");
                user.setPassword(PasswordUtils.decrypt(password));

                user.setFullName(resultSet.getString("fullname"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phoneNumber"));
                user.setAddress(resultSet.getString("address"));
                user.setRole(resultSet.getString("role"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public int updateUser(User user) {
        int rows = 0;
        String sql = "UPDATE bookshopdb.user \n" +
                "SET \n" +
                "    username = ?, \n" +
                "    password = ?, \n" +
                "    fullname = ?, \n" +
                "    email = ?, \n" +
                "    phoneNumber = ?, \n" +
                "    address = ?, \n" +
                "    role = ?\n" +
                "WHERE id = ?";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setString(7, user.getRole());
            preparedStatement.setInt(8, user.getId());
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }
    @Override
    public int deleteUser(int id) {
        int rows = 0;
        String sql = "delete from bookshopdb.user where id = ?";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }
}
