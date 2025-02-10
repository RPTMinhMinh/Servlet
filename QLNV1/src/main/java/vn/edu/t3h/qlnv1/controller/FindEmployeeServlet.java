package vn.edu.t3h.qlnv1.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.qlnv1.dao.EmployeeDao;
import vn.edu.t3h.qlnv1.dao.impl.EmployeeDaoImpl;
import vn.edu.t3h.qlnv1.model.Employee;
import vn.edu.t3h.qlnv1.service.EmployeeService;
import vn.edu.t3h.qlnv1.service.impl.EmployeeServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "FindEmployeeServlet", value = "")
public class FindEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        super.init();
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        employeeService = new EmployeeServiceImpl(employeeDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String salary = req.getParameter("salary");
        String fromDate = req.getParameter("fromDate");
        String toDate = req.getParameter("toDate");
        String position = req.getParameter("position");

        List<Employee> employees = employeeService.findByCondition(name, salary, fromDate, toDate, position);
        req.setAttribute("employees", employees);
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }
}
