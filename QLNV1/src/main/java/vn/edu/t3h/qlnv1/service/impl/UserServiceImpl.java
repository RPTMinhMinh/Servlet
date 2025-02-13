package vn.edu.t3h.qlnv1.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import vn.edu.t3h.qlnv1.dao.RoleDao;
import vn.edu.t3h.qlnv1.dao.UserDao;
import vn.edu.t3h.qlnv1.model.RoleModel;
import vn.edu.t3h.qlnv1.model.UserModel;
import vn.edu.t3h.qlnv1.service.UserService;
import vn.edu.t3h.qlnv1.utils.Constants;
import vn.edu.t3h.qlnv1.utils.PasswordUtils;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleDao roleDao;
    public UserServiceImpl(UserDao userDao,RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public String login(String username, String password, HttpServletRequest request) {
        String passwordEncrypted = PasswordUtils.encrypt(password);
        UserModel user = userDao.findUserByUserNameAndPassword(username,passwordEncrypted);
        String urlRedirect = "";
        if(user == null) {
            urlRedirect = "/login?message="+Constants.LOGIN_ERROR;
            return urlRedirect;
        }
        RoleModel roleModel = roleDao.findRoleById(user.getRoleId());
        if(roleModel == null) {
            urlRedirect = "/login?message="+Constants.PERMISSION_DENIED;
            return urlRedirect;
        }
        request.getSession().setAttribute(Constants.SESSION_ID_CURRENT_USER, user);
        if (roleModel.getCode().equals(Constants.ROLE.ROLE_ADMIN.name())){
            urlRedirect = "/cms/employee";
        }else {
            urlRedirect = "/home";
        }
        return urlRedirect;

    }
}
