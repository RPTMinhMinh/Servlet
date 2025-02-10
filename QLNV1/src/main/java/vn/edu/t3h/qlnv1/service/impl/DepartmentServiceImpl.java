package vn.edu.t3h.qlnv1.service.impl;

import vn.edu.t3h.qlnv1.dao.DepartmentDao;
import vn.edu.t3h.qlnv1.model.Department;
import vn.edu.t3h.qlnv1.service.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }
}
