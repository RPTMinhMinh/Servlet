package vn.edu.t3h.qlns.controller;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.qlns.model.Employee;
import vn.edu.t3h.qlns.service.EmployeeService;
import vn.edu.t3h.qlns.service.EmployeeServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "EmployeeServlet", value = "/employee")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeService employeeService = new EmployeeServiceImpl();
        List<Employee> employees = employeeService.getAllEmployees();
        req.setAttribute("employeeData", employees);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("employee-list.jsp");
        requestDispatcher.forward(req, resp);
    }
}
