package vn.edu.t3h.qlnv.dao.impl;

import vn.edu.t3h.qlnv.dao.EmployeeDao;
import vn.edu.t3h.qlnv.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public List<Employee> findAll() {
        Connection connection = getConnection();
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT e.employee_id, e.name, e.position, e.salary, d.department_name, e.hire_date \n" +
                "                FROM qlns.employees e\n" +
                "                JOIN qlns.departments d ON e.department_id = d.department_id";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(resultSet.getInt("employee_id"));
                employee.setName(resultSet.getString("name"));
                employee.setPosition(resultSet.getString("position"));
                employee.setSalary(resultSet.getString("salary"));
                employee.setDepartment_name(resultSet.getString("department_name"));
                employee.setHireDate(resultSet.getString("hire_date"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return employees;
    }

    @Override
    public List<Employee> getEmployees(String name, String salary, String from, String to, String position, String department) {
        Connection connection = getConnection();
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT e.employee_id, e.name, e.position, e.salary, d.department_name, e.hire_date\n" +
                "FROM employees e\n" +
                "         LEFT JOIN departments d ON e.department_id = d.department_id\n" +
                "WHERE 1=1\n";
        if (name != null && !name.isEmpty()) sql += "AND e.name LIKE ? ";
        if (salary != null && !salary.isEmpty()) sql += "AND e.salary = ? ";
        if (from != null ) sql += "AND e.hire_date >= ? ";
        if (to != null ) sql += "AND e.hire_date <= ? ";
        if (position != null && !position.isEmpty()) sql += "AND e.position LIKE ? ";
        if (department != null && !department.isEmpty()) sql += "AND d.department_name LIKE ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int i = 1;
            if (name != null && !name.isEmpty()) preparedStatement.setString(i++, "%" + name + "%");
            if (salary != null && !salary.isEmpty()) preparedStatement.setDouble(i++, Double.parseDouble(salary));
            if (from != null ) preparedStatement.setString(i++, from);
            if (to != null ) preparedStatement.setString(i++, to);
            if (position != null && !position.isEmpty()) preparedStatement.setString(i++, position);
            if (department != null && !department.isEmpty()) preparedStatement.setString(i++, department);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(resultSet.getInt("employee_id"));
                employee.setName(resultSet.getString("name"));
                employee.setPosition(resultSet.getString("position"));
                employee.setSalary(resultSet.getString("salary"));
                employee.setDepartment_name(resultSet.getString("department_name"));
                employee.setHireDate(resultSet.getString("hire_date"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return employees;
    }

    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3307/qlns";
        String user = "root";
        String password = "admin";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
