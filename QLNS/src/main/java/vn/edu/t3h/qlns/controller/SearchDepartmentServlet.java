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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchDepartmentServlet", value = "/search_department_by_name")
public class SearchDepartmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("departmentName");
        DepartmentService departmentService = new DepartmentServiceImpl();
        List<Department> list = departmentService.getDepartmentsByName(name);
        req.setAttribute("searchDepartments", list);

        RequestDispatcher dispatcher = req.getRequestDispatcher("search_department.jsp");
        dispatcher.forward(req, resp);
    }
}
