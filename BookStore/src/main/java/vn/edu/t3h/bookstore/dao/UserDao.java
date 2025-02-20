package vn.edu.t3h.bookstore.dao;

import vn.edu.t3h.bookstore.model.User;

import java.util.List;

public interface UserDao {
    User findUserByUserNameAndPassword(String username, String password);

    List<User> getAllUsers();

    int insertUser(User user);

    User getUserById(int id);
    int updateUser(User user);
    int deleteUser(int id);
}
