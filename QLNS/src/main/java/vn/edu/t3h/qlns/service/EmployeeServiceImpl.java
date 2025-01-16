package vn.edu.t3h.qlns.service;

import vn.edu.t3h.qlns.dao.EmployeeDao;
import vn.edu.t3h.qlns.dao.impl.EmployeeDaoImpl;
import vn.edu.t3h.qlns.model.Employee;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public List<Employee> getAllEmployees() {
        EmployeeDao dao = new EmployeeDaoImpl();
        return dao.getAllEmployee();
    }
}
