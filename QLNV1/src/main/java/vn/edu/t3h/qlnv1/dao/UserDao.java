package vn.edu.t3h.qlnv1.dao;

import vn.edu.t3h.qlnv1.model.UserModel;

public interface UserDao {
    UserModel findUserByUserNameAndPassword(String username, String password);
}
