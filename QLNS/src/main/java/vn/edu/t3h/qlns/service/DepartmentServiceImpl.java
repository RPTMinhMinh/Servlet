package vn.edu.t3h.qlns.service;

import vn.edu.t3h.qlns.dao.DepartmentDao;
import vn.edu.t3h.qlns.dao.impl.DepartmentDaoImpl;
import vn.edu.t3h.qlns.model.Department;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    @Override
    public List<Department> getAllDepartments() {
        DepartmentDao dao = new DepartmentDaoImpl();
        return dao.getAllDepartments();
    }

    @Override
    public List<Department> getDepartmentsByName(String departmentName) {
        DepartmentDao dao = new DepartmentDaoImpl();
        return dao.getDepartmentsByName(departmentName);
    }
}
