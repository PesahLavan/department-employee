package com.belyaev.servlets;

import com.belyaev.action.DepartmentAction;
import com.belyaev.action.EmployeeAction;
import com.belyaev.form.EmployeeForm;
import com.belyaev.model.Department;
import com.belyaev.model.Employee;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Pavel Belyaev
 * @since 01-Dec-17
 */

@WebServlet(name = "EmployeeServletRD",
        urlPatterns = {"/employee_read", "/employee_delete", "/employee_input_update", "/employee_input"})
public class EmployeeServletRD extends BaseEmployeeServlet {
    private static final Logger log = Logger.getLogger(EmployeeServletRD.class);

    protected void process(HttpServletRequest request, HttpServletResponse response){
        DepartmentAction departmentAction = new DepartmentAction();
        EmployeeAction employeeAction = new EmployeeAction();
        String uri = request.getRequestURI();
        int lastIndex = uri.lastIndexOf('/');
        String action = uri.substring(lastIndex + 1);
        String dispatchUrl = null;
        List<Department> departments = departmentAction.getEmpAll();
        int departmentId;
        request.setAttribute("departments", departments);
        if (action.equals("employee_read")) {
            departmentId = Integer.parseInt(request.getParameter("departmentId"));
            List<Employee> employees = employeeAction.getEmpDep(departmentId);
            request.setAttribute("nameDepartment", departmentName(departmentId));
            request.setAttribute("departmentId", departmentId);
            request.setAttribute("employees", employees);
            dispatchUrl = "/view/employee/EmployeeList.jsp";
        } else if (action.equals("employee_delete")){
            int id = Integer.parseInt(request.getParameter("id"));
            Employee employee = employeeAction.get(id);
            employeeAction.delete(employee);
            dispatchUrl = "/employee_read?departmentId=" + employee.getDepartmentId();
        } else if (action.equals("employee_input")) {
            EmployeeForm employeeForm = new EmployeeForm();
            redirectForm(request, response, null, employeeForm);
        } else  if (action.equals("employee_input_update")){
            int id = Integer.parseInt(request.getParameter("id"));
            Employee employee = employeeAction.get(id);
            EmployeeForm employeeForm = createEmployeeForm(employee);
            redirectForm(request, response, null, employeeForm);
        }
        if (dispatchUrl != null) {
            RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                log.error("Servlet error redirect", e);
            } catch (IOException e) {
                log.error("IO error redirect", e);
            }
        }
    }
    private String departmentName(int departmentId){
        DepartmentAction departmentAction = new DepartmentAction();
        return departmentAction.get(departmentId).getName();
    }
}
