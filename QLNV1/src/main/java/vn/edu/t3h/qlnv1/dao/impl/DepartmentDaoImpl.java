package vn.edu.t3h.qlnv1.dao.impl;

import vn.edu.t3h.qlnv1.dao.DepartmentDao;
import vn.edu.t3h.qlnv1.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {

    @Override
    public List<Department> getAllDepartments() {
        Connection connection = getConnection();
        List<Department> departments = new ArrayList<>();
        String sql = "select * from qlns.departments";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Department department = new Department();
                department.setDepartmentId(resultSet.getInt("department_id"));
                department.setDepartmentName(resultSet.getString("department_name"));
                department.setLocation(resultSet.getString("location"));
                departments.add(department);
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
        return departments;
    }

    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3307/quanlynhansu";
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
