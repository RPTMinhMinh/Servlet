package vn.edu.t3h.qlnv.sevice;

import vn.edu.t3h.qlnv.dao.EmployeeDao;
import vn.edu.t3h.qlnv.dao.impl.EmployeeDaoImpl;
import vn.edu.t3h.qlnv.model.Employee;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public List<Employee> findAll() {
        EmployeeDao dao = new EmployeeDaoImpl();
        return dao.findAll();
    }

    @Override
    public List<Employee> getEmployees(String name, String salary, String from, String to, String position, String department) {
        EmployeeDao dao = new EmployeeDaoImpl();
        return dao.getEmployees(name, salary, from, to, position, department);
    }
}
