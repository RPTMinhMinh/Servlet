package vn.edu.t3h.qlnv1.service.impl;

import com.mysql.cj.util.StringUtils;
import vn.edu.t3h.qlnv1.dao.EmployeeDao;
import vn.edu.t3h.qlnv1.model.Department;
import vn.edu.t3h.qlnv1.model.Employee;
import vn.edu.t3h.qlnv1.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployee();
    }

    @Override
    public int insertEmployee(Employee employee) {
        return employeeDao.insertEmployee(employee);
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeDao.getEmployeeById(id);
    }

    @Override
    public int updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    @Override
    public int deleteEmployeeById(int id) {
        return employeeDao.deleteEmployeeById(id);
    }

    @Override
    public List<Employee> findByCondition(String name, String salary, String fromDate, String toDate, String position) {
        if(StringUtils.isNullOrEmpty(name)){
            name = null;
        }
        if (StringUtils.isNullOrEmpty(salary)){
            salary = null;
        }
        if (StringUtils.isNullOrEmpty(fromDate)){
            fromDate = null;
        }
        if (StringUtils.isNullOrEmpty(toDate)){
            toDate = null;
        }
        if (StringUtils.isNullOrEmpty(position)) {
            position = null;
        }
        return employeeDao.findByCondition(name, salary, fromDate, toDate, position);
    }
}
