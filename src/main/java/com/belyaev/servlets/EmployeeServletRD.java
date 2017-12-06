package com.belyaev.servlets;

import com.belyaev.form.EmployeeForm;
import com.belyaev.model.Department;
import com.belyaev.model.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * TODO: comment
 *
 * @author Pavel Belyaev
 * @since 01-Dec-17
 */

@WebServlet(name = "EmployeeServletRD",
        urlPatterns = {"/employee_read", "/employee_delete", "/employee_input_update", "/employee_input"})
public class EmployeeServletRD extends BaseEmployeeServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uri = request.getRequestURI();
        int lastIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastIndex + 1);
        String dispatchUrl = null;
        List<Department> departments = departmentAction.getEmpAll();
        request.setAttribute("departments", departments);
        if (action.equals("employee_read")) {
            int departmentId = Integer.parseInt(request.getParameter("departmentId"));
            List<Employee> employees = employeeAction.getEmpDep(departmentId);
            String nameDepartment = departmentAction.get(departmentId).getName();
            request.setAttribute("nameDepartment", nameDepartment);
            request.setAttribute("departmentId", departmentId);
            request.setAttribute("employees", employees);
            dispatchUrl = "/view/employee/EmployeeList.jsp";
        } else if (action.equals("employee_delete")){
            int id = Integer.parseInt(request.getParameter("id"));
            Employee employee = employeeAction.get(id);
            int departmentId = employee.getDepartmentId();
            request.setAttribute("departmentId", departmentId);
            employeeAction.delete(employee);
            dispatchUrl = "/employee_read?departmentId=" + departmentId;
        } else if (action.equals("employee_input")) {
            EmployeeForm employeeForm = new EmployeeForm();
            int departmentId = Integer.parseInt(request.getParameter("departmentId"));
            request.setAttribute("departments", departmentAction.getEmpAll());
            String nameDepartment = departmentAction.get(departmentId).getName();
            request.setAttribute("nameDepartment", nameDepartment);
            request.setAttribute("departmentId", departmentId);
            request.setAttribute("employeeForm", employeeForm);
            dispatchUrl = "/view/employee/EmployeeForm.jsp";
        } else  if (action.equals("employee_input_update")){
            int id = Integer.parseInt(request.getParameter("id"));
            Employee employee = employeeAction.get(id);
            EmployeeForm employeeForm = createEmployeeForm(employee);
             int departmentId = employee.getDepartmentId();
            request.setAttribute("departments", departmentAction.getEmpAll());
            String nameDepartment = departmentAction.get(departmentId).getName();
            request.setAttribute("nameDepartment", nameDepartment);
            request.setAttribute("departmentId", departmentId);
            request.setAttribute("employeeForm", employeeForm);
            dispatchUrl = "/view/employee/EmployeeForm.jsp";
        }
        if (dispatchUrl != null) {
            RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }
    }
}
