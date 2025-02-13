package vn.edu.t3h.qlnv1.dao.impl;

import vn.edu.t3h.qlnv1.dao.DepartmentDao;
import vn.edu.t3h.qlnv1.dao.EmployeeDao;
import vn.edu.t3h.qlnv1.model.Department;
import vn.edu.t3h.qlnv1.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public int insertEmployee(Employee employee) {
        int rows = 0;
        Connection connection = getConnection();
        String sql = "insert into quanlynhansu.employees (name, position, salary, department_id, hire_date) values (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getPosition());
            preparedStatement.setDouble(3, employee.getSalary());
            preparedStatement.setInt(4, employee.getDepartmentId());
            preparedStatement.setString(5, employee.getHireDate());
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = null;
        Connection connection = getConnection();
        String sql = "SELECT e.employee_id, e.name, e.position, e.salary, e.department_id, e.hire_date\n" +
                "FROM quanlynhansu.employees e WHERE e.employee_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employee = new Employee();
                employee.setId(resultSet.getInt("employee_id"));
                employee.setName(resultSet.getString("name"));
                employee.setPosition(resultSet.getString("position"));
                employee.setSalary(resultSet.getDouble("salary"));
                employee.setDepartmentId(resultSet.getInt("department_id"));
                employee.setHireDate(resultSet.getString("hire_date"));
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
        return employee;
    }

    @Override
    public int updateEmployee(Employee employee) {
        int rows = 0;
        Connection connection = getConnection();
        String sql = "UPDATE quanlynhansu.employees SET name = ?, position = ?, salary = ?, department_id = ?, hire_date = ? \n" +
                "                      WHERE employee_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getPosition());
            preparedStatement.setDouble(3, employee.getSalary());
            preparedStatement.setInt(4, employee.getDepartmentId());
            preparedStatement.setString(5, employee.getHireDate());
            preparedStatement.setInt(6, employee.getId());
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
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
        return rows;
    }

    @Override
    public int deleteEmployeeById(int id) {
        int rows = 0;
        Connection connection = getConnection();
        String sql = "DELETE FROM quanlynhansu.employees WHERE employee_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
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
        return rows;
    }

    @Override
    public List<Employee> getAllEmployee() {
        Connection connection = getConnection();
        List<Employee> employees = new ArrayList<>();
        String sql = "select * from employees emp " +
                "inner join departments dept on emp.department_id = dept.department_id";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("employee_id"));
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

    @Override
    public List<Employee> findByCondition(String name, String salary, String fromDate, String toDate, String position) {
        Connection connection = getConnection();
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT e.employee_id, e.name, e.position, e.salary, d.department_name, e.hire_date\n" +
                "FROM employees e\n" +
                "         LEFT JOIN departments d ON e.department_id = d.department_id\n" +
                "WHERE (e.name LIKE ? OR ? IS NULL)\n" +
                "  AND (e.salary = ? OR ? IS NULL)\n" +
                "  AND (e.hire_date >= ? or ? is NULL)\n" +
                "  AND (e.hire_date <= ? or ? is NULL)\n" +
                "  AND (e.position LIKE ? OR ? IS NULL)\n;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            setConditionFilter(name, salary, fromDate, toDate, position, preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("employee_id"));
                employee.setName(resultSet.getString("name"));
                employee.setPosition(resultSet.getString("position"));
                employee.setSalary(resultSet.getDouble("salary"));
                employee.setDepartmentName(resultSet.getString("department_name"));
                employee.setHireDate(resultSet.getString("hire_date"));
                employees.add(employee);
            }
        } catch (SQLException e) {
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

    private static void setConditionFilter(String name, String salary, String fromDate, String toDate, String position, PreparedStatement statement) throws SQLException {
        if (name != null) {
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + name + "%");
        } else {
            statement.setNull(1, Types.VARCHAR);
            statement.setNull(2, Types.VARCHAR);
        }

        if (salary != null) {
            statement.setLong(3, Long.parseLong(salary));
            statement.setLong(4, Long.parseLong(salary));
        } else {
            statement.setNull(3, Types.DECIMAL);
            statement.setNull(4, Types.DECIMAL);
        }

        if (fromDate != null) {
            statement.setString(5, fromDate);
            statement.setString(6, fromDate);
        } else {
            statement.setNull(5, Types.VARCHAR);
            statement.setNull(6, Types.VARCHAR);
        }

        if (toDate != null) {
            statement.setString(7, toDate);
            statement.setString(8, toDate);
        } else {
            statement.setNull(7, Types.VARCHAR);
            statement.setNull(8, Types.VARCHAR);
        }

        if (position != null) {
            statement.setString(9, "%" + position + "%");
            statement.setString(10, "%" + position + "%");
        } else {
            statement.setNull(9, Types.VARCHAR);
            statement.setNull(10, Types.VARCHAR);
        }
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
