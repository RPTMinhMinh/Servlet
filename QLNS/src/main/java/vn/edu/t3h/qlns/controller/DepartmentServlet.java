package vn.edu.t3h.qlns.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.qlns.model.Department;
import vn.edu.t3h.qlns.service.DepartmentService;
import vn.edu.t3h.qlns.service.DepartmentServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DepartmentServletShowAll", value = "/show_department")
public class DepartmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DepartmentService departmentService = new DepartmentServiceImpl();
        List<Department> departments = departmentService.getAllDepartments();
        req.setAttribute("showDepartments", departments);

        RequestDispatcher dispatcher = req.getRequestDispatcher("show-department.jsp");
        dispatcher.forward(req, resp);
    }
}
