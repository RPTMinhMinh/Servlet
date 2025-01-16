package vn.edu.t3h.qlnv.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.qlnv.model.Employee;
import vn.edu.t3h.qlnv.sevice.EmployeeService;
import vn.edu.t3h.qlnv.sevice.EmployeeServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowEmployeesServlet", value = "")
public class ShowEmployeesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeService employeeService = new EmployeeServiceImpl();
        List<Employee> employees = employeeService.findAll();
        req.setAttribute("employees", employees);

        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }
}
