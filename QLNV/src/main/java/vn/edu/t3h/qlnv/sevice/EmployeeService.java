package vn.edu.t3h.qlnv.sevice;

import vn.edu.t3h.qlnv.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    List<Employee> getEmployees(String name, String salary, String from, String to, String position, String department);
}
