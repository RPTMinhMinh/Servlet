package vn.edu.t3h.qlns.dao.impl;

import vn.edu.t3h.qlns.dao.EmployeeDao;
import vn.edu.t3h.qlns.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public List<Employee> getAllEmployee() {
        Connection connection = getConnection();
        List<Employee> employees = new ArrayList<>();
        String sql = "select * from employees e\n" +
                "    join qlns.departments d on d.department_id = e.department_id;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setName(resultSet.getString("name"));
                employee.setPosition(resultSet.getString("position"));
                employee.setSalary(resultSet.getDouble("salary"));
                employee.setDepartmentName(resultSet.getString("department_name"));
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

    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3307/qlns";
        String user = "root";
        String password = "admin";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
