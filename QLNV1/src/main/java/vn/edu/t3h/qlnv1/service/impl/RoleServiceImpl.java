package vn.edu.t3h.qlnv1.service.impl;

import vn.edu.t3h.qlnv1.dao.RoleDao;
import vn.edu.t3h.qlnv1.model.RoleModel;
import vn.edu.t3h.qlnv1.service.RoleService;

public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public RoleModel getRoleById(Integer roleId) {
        return roleDao.findRoleById(roleId);
    }
}
