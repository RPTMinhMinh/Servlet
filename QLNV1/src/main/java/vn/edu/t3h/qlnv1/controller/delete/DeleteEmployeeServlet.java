package vn.edu.t3h.qlnv1.controller.delete;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.qlnv1.dao.EmployeeDao;
import vn.edu.t3h.qlnv1.dao.impl.EmployeeDaoImpl;
import vn.edu.t3h.qlnv1.service.EmployeeService;
import vn.edu.t3h.qlnv1.service.impl.EmployeeServiceImpl;

import java.io.IOException;
// _ = -
@WebServlet(name = "DeleteEmployeeServlet", value = "/delete_employee")
public class DeleteEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        super.init();
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        employeeService = new EmployeeServiceImpl(employeeDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        employeeService.deleteEmployeeById(id);
        resp.sendRedirect("/");
    }
}
