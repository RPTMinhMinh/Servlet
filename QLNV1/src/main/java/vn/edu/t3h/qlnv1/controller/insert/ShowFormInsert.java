package vn.edu.t3h.qlnv1.controller.insert;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.qlnv1.dao.DepartmentDao;
import vn.edu.t3h.qlnv1.dao.impl.DepartmentDaoImpl;
import vn.edu.t3h.qlnv1.model.Department;
import vn.edu.t3h.qlnv1.service.DepartmentService;
import vn.edu.t3h.qlnv1.service.impl.DepartmentServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowFormInsert", value = "/form_insert")
public class ShowFormInsert  extends HttpServlet {
    private DepartmentService departmentService;

    @Override
    public void init() throws ServletException{
        super.init();
        DepartmentDao departmentDao = new DepartmentDaoImpl();
        departmentService = new DepartmentServiceImpl(departmentDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departments = departmentService.getAllDepartments();
        req.setAttribute("departments", departments);
        RequestDispatcher view = req.getRequestDispatcher("add_employee.jsp");
        view.forward(req, resp);
    }
}
