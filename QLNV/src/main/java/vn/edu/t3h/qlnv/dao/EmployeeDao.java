package vn.edu.t3h.qlnv.dao;

import vn.edu.t3h.qlnv.model.Employee;

import java.util.Date;
import java.util.List;

public interface EmployeeDao {
    List<Employee> findAll();
    List<Employee> getEmployees(String name, String salary, String from, String to, String position, String department);
}
