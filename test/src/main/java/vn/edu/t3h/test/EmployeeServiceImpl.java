package vn.edu.t3h.test;

import com.mysql.cj.util.StringUtils;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Department> getAllDepartments() {
        return employeeDao.getAllDepartment();
    }
}
