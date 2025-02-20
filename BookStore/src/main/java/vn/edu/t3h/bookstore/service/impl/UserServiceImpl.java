package vn.edu.t3h.bookstore.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import vn.edu.t3h.bookstore.dao.UserDao;
import vn.edu.t3h.bookstore.model.User;
import vn.edu.t3h.bookstore.service.UserService;
import vn.edu.t3h.bookstore.utils.Constants;
import vn.edu.t3h.bookstore.utils.PasswordUtils;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String login(String username, String password, HttpServletRequest request) {
        String passwordEncrypted = PasswordUtils.encrypt(password);
        User user = userDao.findUserByUserNameAndPassword(username, passwordEncrypted);
        String urlRedirect = "";
        if(user == null){
            urlRedirect = "/login?message="+ Constants.LOGIN_ERROR;
            return urlRedirect;
        }
//        if(user.getRole().equals("EMPLOYEE") || user.getRole().equals("CUSTOMER"))
        if (user.getRole() == null){
            urlRedirect = "/login?message="+ Constants.PERMISSION_DENIED;
            return urlRedirect;
        }
        request.getSession().setAttribute(Constants.SESSION_ID_CURRENT_USER, user);
        if(user.getRole().equals(Constants.ROLE.ADMIN.name()) || user.getRole().equals(Constants.ROLE.EMPLOYEE.name())){
            urlRedirect = "/cms/book_management";
        }else {
            urlRedirect = "/home";
        }
        return urlRedirect;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public int insertUser(User user) {
        user.setPassword(PasswordUtils.encrypt(user.getPassword()));
        return userDao.insertUser(user);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public int updateUser(User user) {
        user.setPassword(PasswordUtils.encrypt(user.getPassword()));
        return userDao.updateUser(user);
    }
    @Override
    public int deleteUser(int id) {
        return userDao.deleteUser(id);
    }

}
