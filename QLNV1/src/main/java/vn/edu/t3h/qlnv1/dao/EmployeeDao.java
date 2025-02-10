package vn.edu.t3h.qlnv1.dao;

import vn.edu.t3h.qlnv1.model.Department;
import vn.edu.t3h.qlnv1.model.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> getAllEmployee();
    List<Employee> findByCondition(String name, String salary, String fromDate, String toDate, String position);
    int insertEmployee(Employee employee);
    Employee getEmployeeById(int id);
    int updateEmployee(Employee employee);
    int deleteEmployeeById(int id);
}
