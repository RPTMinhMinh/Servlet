package vn.edu.t3h.qlns.service;

import vn.edu.t3h.qlns.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartments();
    List<Department> getDepartmentsByName(String departmentName);
}
