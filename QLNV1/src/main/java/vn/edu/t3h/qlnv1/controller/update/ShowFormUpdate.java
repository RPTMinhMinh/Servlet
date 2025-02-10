package vn.edu.t3h.qlnv1.controller.update;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.qlnv1.dao.DepartmentDao;
import vn.edu.t3h.qlnv1.dao.EmployeeDao;
import vn.edu.t3h.qlnv1.dao.impl.DepartmentDaoImpl;
import vn.edu.t3h.qlnv1.dao.impl.EmployeeDaoImpl;
import vn.edu.t3h.qlnv1.model.Department;
import vn.edu.t3h.qlnv1.model.Employee;
import vn.edu.t3h.qlnv1.service.DepartmentService;
import vn.edu.t3h.qlnv1.service.EmployeeService;
import vn.edu.t3h.qlnv1.service.impl.DepartmentServiceImpl;
import vn.edu.t3h.qlnv1.service.impl.EmployeeServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowFormUpdate", value = "/form_update")
public class ShowFormUpdate extends HttpServlet {
    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @Override
    public void init() throws ServletException {
        super.init();
        DepartmentDao departmentDao = new DepartmentDaoImpl();
        departmentService = new DepartmentServiceImpl(departmentDao);
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        employeeService = new EmployeeServiceImpl(employeeDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int employeeId = Integer.parseInt(req.getParameter("id"));
        Employee employee = employeeService.getEmployeeById(employeeId);
        List<Department> departments = departmentService.getAllDepartments();

        req.setAttribute("departments", departments);
        req.setAttribute("employee", employee);
        RequestDispatcher dispatcher = req.getRequestDispatcher("update_employee.jsp");
        dispatcher.forward(req, resp);
    }
}
