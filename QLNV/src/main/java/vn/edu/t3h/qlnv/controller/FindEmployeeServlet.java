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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "FindEmployeeServlet", value = "/find_employees")
public class FindEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String salary = req.getParameter("salary");
        String fromHireDate = req.getParameter("fromHireDate");
        String toHireDate = req.getParameter("toHireDate");
        String position = req.getParameter("position");
        String departmentName = req.getParameter("departmentName");

        fromHireDate = convertDate(fromHireDate,"dd/MM/yyyy", "yyyy-MM-dd");
        toHireDate = convertDate(toHireDate,"dd/MM/yyyy", "yyyy-MM-dd");

        EmployeeService employeeService = new EmployeeServiceImpl();
        List<Employee> employees = employeeService.getEmployees(name, salary,fromHireDate,toHireDate,position,departmentName);
        req.setAttribute("find_employees", employees);

        RequestDispatcher dispatcher = req.getRequestDispatcher("find_employees.jsp");
        dispatcher.forward(req, resp);
    }

    private String convertDate(String date, String fromFormat, String toFormat) {
        if(date != null && !date.isEmpty()){
            try {
                SimpleDateFormat fromFormater = new SimpleDateFormat(fromFormat);
                SimpleDateFormat toFormater = new SimpleDateFormat(toFormat);
                Date date1 = fromFormater.parse(date);
                return toFormater.format(date1);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
