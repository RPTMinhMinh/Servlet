package vn.edu.t3h.qlns.dao;

import vn.edu.t3h.qlns.model.Department;

import java.util.List;

public interface DepartmentDao {
    List<Department> getAllDepartments();
    List<Department> getDepartmentsByName(String departmentName);
}
