package vn.edu.t3h.bookstore.service;

import jakarta.servlet.http.HttpServletRequest;
import vn.edu.t3h.bookstore.model.User;

import java.util.List;

public interface UserService {
    public String login(String username, String password, HttpServletRequest request);
    List<User> getAllUsers();
    int insertUser(User user);
    User getUserById(int id);
    int updateUser(User user);
    int deleteUser(int id);
}
